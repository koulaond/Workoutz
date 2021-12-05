package com.ondrejkoula.service;

import com.ondrejkoula.domain.superset.SuperSet;
import com.ondrejkoula.domain.superset.SuperSetExercise;
import com.ondrejkoula.repository.superset.SuperSetExerciseRepository;
import com.ondrejkoula.repository.superset.SuperSetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public SuperSetExercise save(SuperSetExercise superSetExercise) {
        log.info("Saving Super set exercise: {}", superSetExercise);
        SuperSetExercise saved = repository.save(superSetExercise);
        log.info("Super set successfully saved: {}", saved);
        return saved;
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
        List<SuperSetExercise> found = repository.findSuperSetExercisesBySuperSetIdOrderByOrderInSet(parentSetId);
        log.info("Found {} exercises for super set with id {}", found.size(), parentSetId);
        return found;
    }

    public void deleteById(Long id) {
        log.info("Deleting Super set exercise with ID:  " + id);
        repository.deleteById(id);
    }

}
