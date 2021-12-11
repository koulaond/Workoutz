package com.ondrejkoula.service;

import com.ondrejkoula.domain.superset.SuperSet;
import com.ondrejkoula.domain.superset.SuperSetExercise;
import com.ondrejkoula.dto.SuperSetExerciseChangePositionDTO;
import com.ondrejkoula.dto.SuperSetExerciseDTO;
import com.ondrejkoula.exception.ValidationException;
import com.ondrejkoula.repository.superset.SuperSetExerciseRepository;
import com.ondrejkoula.repository.superset.SuperSetRepository;
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
public class SuperSetExerciseService {

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

    public List<SuperSetExercise> findByParentSetId(Long parentSetId) {
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


    public SuperSetExercise insertExerciseToSet(Long parentSetId, SuperSetExerciseDTO exerciseDTO) {
        Optional<SuperSet> parentSet = superSetRepository.findById(parentSetId);
        if (!parentSet.isPresent()) {
            throwException("Parent super set not found.");
        }

        if (isNull(exerciseDTO.getPosition())) {
            throwException("Position not defined.");
        }

        long countBySuperSetId = repository.countBySuperSetId(parentSetId);
        if (countBySuperSetId < exerciseDTO.getPosition() || exerciseDTO.getPosition() < 0) {
            throwException(format("Position %s is out of range. Total exercises in set: %s", exerciseDTO.getPosition(), countBySuperSetId));
        }

        List<SuperSetExercise> exercisesAfter = repository.findByPositionGreaterThan(parentSetId, exerciseDTO.getPosition());
        log.info("Updating positions of all exercises with position greater than {}", exerciseDTO.getPosition());

        exercisesAfter.forEach(exercise -> {
            exercise.setPosition(exercise.getPosition() + 1);
            repository.save(exercise);
        });

        log.info("Saving new exercise on position {}", exerciseDTO.getPosition());
        SuperSetExercise newExercise = SuperSetExercise.from(exerciseDTO);
        newExercise.setSuperSet(parentSet.get());

        return repository.save(newExercise);
    }


    public SuperSetExercise changeExercisePosition(SuperSetExerciseChangePositionDTO dto) {
        Optional<SuperSetExercise> search = repository.findById(dto.getId());

        if (!search.isPresent()) {
            throwException("Exercise not found.");
        }

        SuperSetExercise exercise = search.get();
        long countBySuperSetId = repository.countBySuperSetId(exercise.getSuperSet().getId());

        if (countBySuperSetId < dto.getNewPosition() || dto.getNewPosition() < 0) {
            throwException(format("Position %s is out of range. Total exercises in set: %s", dto.getNewPosition(), countBySuperSetId));
        }

        Integer oldPosition = exercise.getPosition();
        List<SuperSetExercise> exercisesBetween = getExercisesBetween(exercise.getParent().getId(), oldPosition, dto.getNewPosition());

        exercisesBetween.forEach(superSetExercise -> {
            if (Objects.equals(dto.getNewPosition(), oldPosition)) {
                return;
            }
            superSetExercise.setPosition(dto.getNewPosition() > oldPosition ? superSetExercise.getPosition() - 1 : superSetExercise.getPosition() + 1);
            repository.save(superSetExercise);
        });
        exercise.setPosition(dto.getNewPosition());

        return repository.save(exercise);
    }

    public void removeExerciseFromSet(Long exerciseId) {
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

    private List<SuperSetExercise> getExercisesBetween(Long parentId, Integer leftBound, Integer rightBound) {
        if (leftBound > rightBound) {
            return repository.findByPositionBetween(parentId, rightBound, leftBound - 1);
        } else {
            return repository.findByPositionBetween(parentId, leftBound + 1, rightBound);
        }
    }

    public void deleteById(Long id) {
        log.info("Deleting Super set exercise with ID:  " + id);
        repository.deleteById(id);
    }

    private void throwException(String message) {
        log.warn(message);
        throw new ValidationException(message, this.getClass().getSimpleName());
    }
}
