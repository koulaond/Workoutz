package com.ondrejkoula.service.superset;

import com.ondrejkoula.domain.superset.SuperSet;
import com.ondrejkoula.domain.superset.SuperSetExercise;
import com.ondrejkoula.exception.ValidationException;
import com.ondrejkoula.repository.superset.SuperSetExerciseRepository;
import com.ondrejkoula.repository.superset.SuperSetRepository;
import com.ondrejkoula.service.ExerciseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@Slf4j
@Component
public class SuperSetExerciseService extends ExerciseService<SuperSetExercise> {

    private final SuperSetExerciseRepository repository;

    private final SuperSetRepository superSetRepository;

    @Autowired
    public SuperSetExerciseService(SuperSetExerciseRepository repository,
                                   SuperSetRepository superSetRepository) {
        this.repository = repository;
        this.superSetRepository = superSetRepository;
    }

    public SuperSetExercise findById(Long id) {
        log.info("Getting Super set exercise with ID:  " + id);
        Optional<SuperSetExercise> found = repository.findById(id);
        if (!found.isPresent()) {
            log.info("Super set exercise with ID:  " + id + " not exists.");
            return null; // TODO throw NotFound exception
        }
        log.info("Super set exercise with ID:  " + id + " found.");
        return found.get();
    }

    @Override
    public List<SuperSetExercise> findByParentId(Long parentSetId) {
        log.info("Getting Super set exercises by parent super set ID:  " + parentSetId);
        Optional<SuperSet> parentSuperSet = superSetRepository.findById(parentSetId);
        if (!parentSuperSet.isPresent()) {
            log.info("Parent Super set with ID:  " + parentSetId + " not exists.");
            return null; // TODO throw ParentNotFoundException
        }
        List<SuperSetExercise> found = repository.findSuperSetExercisesBySuperSetIdOrderByPosition(parentSetId);
        log.info("Found {} exercises for super set with id {}", found.size(), parentSetId);
        return found;
    }


    @Override
    public SuperSetExercise assignNewItemToParent(Long parentSetId, SuperSetExercise newExercise) {
        Optional<SuperSet> parentSet = superSetRepository.findById(parentSetId);
        if (!parentSet.isPresent()) {
            throwException("Parent super set not found.");
        }

        if (isNull(newExercise.getPosition())) {
            throwException("Position not defined.");
        }

        long countBySuperSetId = repository.countBySuperSetId(parentSetId);
        if (countBySuperSetId < newExercise.getPosition() || newExercise.getPosition() < 0) {
            throwException(format("Position %s is out of range. Total exercises in set: %s", newExercise.getPosition(), countBySuperSetId));
        }

        List<SuperSetExercise> exercisesAfter = repository.findByPositionGreaterThan(parentSetId, newExercise.getPosition());
        log.info("Updating positions of all exercises with position greater than {}", newExercise.getPosition());

        exercisesAfter.forEach(exercise -> {
            exercise.setPosition(exercise.getPosition() + 1);
            repository.save(exercise);
        });

        log.info("Saving new exercise on position {}", newExercise.getPosition());
        newExercise.setSuperSet(parentSet.get());

        return repository.save(newExercise);
    }

    @Override
    public SuperSetExercise changeItemPosition(Long id, Integer newPosition) {
        Optional<SuperSetExercise> search = repository.findById(id);

        if (!search.isPresent()) {
            throwException("Exercise not found.");
        }

        SuperSetExercise exercise = search.get();
        long countBySuperSetId = repository.countBySuperSetId(exercise.getSuperSet().getId());

        if (countBySuperSetId < newPosition || newPosition < 0) {
            throwException(format("Position %s is out of range. Total exercises in set: %s", newPosition, countBySuperSetId));
        }

        Integer oldPosition = exercise.getPosition();
        List<SuperSetExercise> exercisesBetween = getExercisesBetween(exercise.getParent().getId(), oldPosition, newPosition);

        exercisesBetween.forEach(superSetExercise -> {
            if (Objects.equals(newPosition, oldPosition)) {
                return;
            }
            superSetExercise.setPosition(newPosition > oldPosition ? superSetExercise.getPosition() - 1 : superSetExercise.getPosition() + 1);
            repository.save(superSetExercise);
        });
        exercise.setPosition(newPosition);

        return repository.save(exercise);
    }

    public void removeExistingItemFromParent(Long exerciseId) {
        Optional<SuperSetExercise> search = repository.findById(exerciseId);
        if (!search.isPresent()) {
            return;
        }
        SuperSetExercise exercise = search.get();
        repository.delete(exercise);
        List<SuperSetExercise> followingExercises = repository.findByPositionGreaterThan(exercise.getParent().getId(), exercise.getPosition());

        followingExercises.forEach(following -> {
            following.setPosition(following.getPosition() - 1);
            repository.save(following);
        });
    }

    public void deleteById(Long id) {
        log.info("Deleting Super set exercise with ID:  " + id);
        repository.deleteById(id);
    }

    private List<SuperSetExercise> getExercisesBetween(Long parentId, Integer leftBound, Integer rightBound) {
        if (leftBound > rightBound) {
            return repository.findByPositionBetween(parentId, rightBound, leftBound - 1);
        } else {
            return repository.findByPositionBetween(parentId, leftBound + 1, rightBound);
        }
    }

    private void throwException(String message) {
        log.warn(message);
        throw new ValidationException(message, this.getClass().getSimpleName());
    }
}
