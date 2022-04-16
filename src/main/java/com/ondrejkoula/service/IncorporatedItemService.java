package com.ondrejkoula.service;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.IncorporatedItem;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.exception.ValidationException;
import com.ondrejkoula.repository.IncorporatedItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Collections.singletonMap;
import static java.util.Objects.isNull;

@Slf4j
public abstract class IncorporatedItemService<
        CH extends IncorporatedItem<P>,
        CHR extends IncorporatedItemRepository<CH> & JpaRepository<CH, Long>,
        P extends DomainEntity,
        PR extends JpaRepository<P, Long>>

        extends GenericService<CH, CHR> {

    protected final PR parentRepository;

    public IncorporatedItemService(CHR repository, PR parentRepository) {
        super(repository);
        this.parentRepository = parentRepository;
    }

    public List<CH> findByParentId(Long parentId) {
        parentRepository.findById(parentId)
                .orElseThrow(() -> new DataNotFoundException("Parent found", "dataNotFound", singletonMap("parentId", parentId.toString())));

        return repository.findByParentIdOrderByPosition(parentId);
    }

    public CH assignNewItemToParent(Long parentSetId, CH newItem) {
        P parent = parentRepository.findById(parentSetId)
                .orElseThrow(() -> new ValidationException("Parent not found.", this.getClass().getSimpleName()));

        if (isNull(newItem.getPosition())) {
            throwException("Position not defined.");
        }

        long countByParent = repository.countByParentId(parentSetId);
        if (countByParent < newItem.getPosition() || newItem.getPosition() < 0) {
            throwException(format("Position %s is out of range. Total items under parent: %s", newItem.getPosition(), countByParent));
        }

        List<CH> found = repository.findByParentIdAndPositionGreaterThanEqual(parentSetId, newItem.getPosition());

        found.forEach(child -> {
            child.setPosition(child.getPosition() + 1);
            repository.save(child);
        });

        newItem.setParent(parent);

        return repository.save(newItem);
    }

    public CH changeItemPosition(Long id, Integer newPosition) {
        CH data = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Data found", "dataNotFound", singletonMap("id", id.toString())));

        long totalCount = repository.countByParentId(data.getParent().getId());

        if (totalCount < newPosition || newPosition < 0) {
            throwException(format("Position %s is out of range. Total items under parent: %s", newPosition, totalCount));
        }

        Integer oldPosition = data.getPosition();
        List<CH> siblingsBetween = getSiblingsBetweenPositions(data.getParent().getId(), oldPosition, newPosition);

        siblingsBetween.forEach(sibling -> {
            if (Objects.equals(newPosition, oldPosition)) {
                return;
            }
            sibling.setPosition(newPosition > oldPosition ? sibling.getPosition() - 1 : sibling.getPosition() + 1);
            repository.save(sibling);
        });
        data.setPosition(newPosition);

        return repository.save(data);
    }

    public void removeExistingItemFromParent(Long idToRemove) {
        Optional<CH> search = repository.findById(idToRemove);

        search.ifPresent(itemToRemove -> {

            repository.delete(itemToRemove);
            List<CH> following = repository.findByParentIdAndPositionGreaterThanEqual(itemToRemove.getParent().getId(), itemToRemove.getPosition());

            following.forEach(next -> {
                next.setPosition(next.getPosition() - 1);
                repository.save(next);
            });
        });
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private List<CH> getSiblingsBetweenPositions(Long parentId, Integer leftBound, Integer rightBound) {
        if (leftBound > rightBound) {
            return repository.findByParentIdAndPositionBetween(parentId, rightBound, leftBound - 1);
        } else {
            return repository.findByParentIdAndPositionBetween(parentId, leftBound + 1, rightBound);
        }
    }

    private void throwException(String message) {
        log.warn(message);
        throw new ValidationException(message, this.getClass().getSimpleName());
    }
}
