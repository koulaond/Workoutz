package com.ondrejkoula.service;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.CompositionChild;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.exception.ValidationException;
import com.ondrejkoula.repository.CompositionChildRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Collections.singletonMap;
import static java.util.Objects.isNull;

@Slf4j
public abstract class CompositionService<CH extends CompositionChild<P>, P extends DomainEntity>
        extends GenericService<P> {

    protected final CompositionChildRepository<CH> childRepository;

    public CompositionService(CompositionChildRepository<CH> childRepository,
                              JpaRepository<P, Long> parentRepository) {
        super(parentRepository);
        this.childRepository = childRepository;
    }

    public List<CH> findChildrenByParent(Long parentId) {
        repository.findById(parentId)
                .orElseThrow(() -> new DataNotFoundException("Parent found", "dataNotFound", singletonMap("parentId", parentId.toString())));

        return childRepository.findByParentIdOrderByPosition(parentId);
    }

    public P assignNewChildToParent(Long parentId, CH newItem) {
        P parent = repository.findById(parentId)
                .orElseThrow(() -> new ValidationException("Parent not found.", this.getClass().getSimpleName()));

        if (isNull(newItem.getPosition())) {
            throwValidationException("Position not defined.");
        }

        long countByParent = childRepository.countByParentId(parentId);
        if (countByParent < newItem.getPosition() || newItem.getPosition() < 0) {
            throwValidationException(format("Position %s is out of range. Total items under parent: %s", newItem.getPosition(), countByParent));
        }

        List<CH> found = childRepository.findByParentIdAndPositionGreaterThanEqual(parentId, newItem.getPosition());

        found.forEach(child -> {
            child.setPosition(child.getPosition() + 1);
            childRepository.save(child);
        });

        newItem.setParent(parent);

        return childRepository.save(newItem).getParent();
    }

    public P changeChildPosition(Long childId, Integer newPosition) {
        CH child = childRepository.findById(childId)
                .orElseThrow(() -> new DataNotFoundException("Data found", "dataNotFound", singletonMap("id", childId.toString())));

        long totalCount = childRepository.countByParentId(child.getParent().getId());

        if (totalCount < newPosition || newPosition < 0) {
            throwValidationException(format("Position %s is out of range. Total items under parent: %s", newPosition, totalCount));
        }

        Integer oldPosition = child.getPosition();
        List<CH> siblingsBetween = getChildrenBetweenPositions(child.getParent().getId(), oldPosition, newPosition);

        siblingsBetween.forEach(sibling -> {
            if (Objects.equals(newPosition, oldPosition)) {
                return;
            }
            sibling.setPosition(newPosition > oldPosition ? sibling.getPosition() - 1 : sibling.getPosition() + 1);
            childRepository.save(sibling);
        });

        child.setPosition(newPosition);

        return childRepository.save(child).getParent();
    }

    public void removeExistingChildFromParent(Long idToRemove) {
        Optional<CH> search = childRepository.findById(idToRemove);

        search.ifPresent(itemToRemove -> {

            childRepository.delete(itemToRemove);
            List<CH> following = childRepository.findByParentIdAndPositionGreaterThanEqual(itemToRemove.getParent().getId(), itemToRemove.getPosition());

            following.forEach(next -> {
                next.setPosition(next.getPosition() - 1);
                childRepository.save(next);
            });
        });
    }

    private List<CH> getChildrenBetweenPositions(Long parentId, Integer leftBound, Integer rightBound) {
        if (leftBound > rightBound) {
            return childRepository.findByParentIdAndPositionBetween(parentId, rightBound, leftBound - 1);
        } else {
            return childRepository.findByParentIdAndPositionBetween(parentId, leftBound + 1, rightBound);
        }
    }

    private void throwValidationException(String message) {
        log.warn(message);
        throw new ValidationException(message, this.getClass().getSimpleName());
    }
}
