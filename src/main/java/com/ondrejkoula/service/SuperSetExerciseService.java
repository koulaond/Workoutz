package com.ondrejkoula.service;

import com.ondrejkoula.domain.superset.SuperSetExercise;
import com.ondrejkoula.repository.superset.SuperSetExerciseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SuperSetExerciseService {

    private final SuperSetExerciseRepository repository;

    @Autowired
    public SuperSetExerciseService(SuperSetExerciseRepository repository) {
        this.repository = repository;
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

    public void deleteById(Long id) {
        log.info("Deleting Super set exercise with ID:  " + id);
        repository.deleteById(id);
    }
}
