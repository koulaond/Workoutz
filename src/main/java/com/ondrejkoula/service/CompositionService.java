package com.ondrejkoula.service;

import com.ondrejkoula.domain.CompositionChildExercise;
import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.exception.*;
import com.ondrejkoula.repository.jpa.CompositionChildRepository;
import com.ondrejkoula.service.dependencies.DependencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Collections.singletonMap;
import static java.util.Objects.isNull;

@Slf4j
public abstract class CompositionService<CH extends CompositionChildExercise<P>, P extends DomainEntity>
        extends GenericService<P> {

    protected final CompositionChildRepository<CH> childRepository;

    public CompositionService(CompositionChildRepository<CH> childRepository,
                              JpaRepository<P, Long> parentRepository, 
                              DependencyService dependencyService) {
        
        super(parentRepository, dependencyService);
        this.childRepository = childRepository;
    }

    public List<CH> findChildrenByParent(Long parentId) {
        repository.findById(parentId)
                .orElseThrow(() -> new DataNotFoundException("Parent not found", "dataNotFound", singletonMap("parentId", parentId.toString())));

        return childRepository.findByParentIdOrderByPosition(parentId);
    }

    public void assignNewChildToParent(Long parentId, CH newItem) {
        P parent = repository.findById(parentId)
                .orElseThrow(() -> new ParentNotFoundException(parentId, this.getClass().getSimpleName()));

        if (isNull(newItem.getPosition())) {
            log.warn("Position not defined.");
            throw new PositionNotDefinedOnChildAssignException("Position not defined.");
        }

        int countByParent = childRepository.countByParentId(parentId);
        validatePositionIsInRange(newItem.getPosition(), countByParent);

        List<CH> found = childRepository.findByParentIdAndPositionGreaterThanEqual(parentId, newItem.getPosition());

        found.forEach(child -> {
            child.setPosition(child.getPosition() + 1);
            childRepository.save(child);
        });

        newItem.setParent(parent);
        childRepository.save(newItem);
    }

    public void changeChildPosition(Long childId, Integer newPosition) {
        CH child = childRepository.findById(childId)
                .orElseThrow(() -> new DataNotFoundException("Data found", "dataNotFound", singletonMap("id", childId.toString())));

        int countByParent = childRepository.countByParentId(child.getParent().getId());

        validatePositionIsInRange(newPosition, countByParent);

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

        childRepository.save(child);
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


    private void validatePositionIsInRange(Integer newPosition, int totalCount) {
        if (totalCount < newPosition || newPosition < 0) {
            throw new PositionOutOfRangeException(totalCount, newPosition);
        }
    }
}
