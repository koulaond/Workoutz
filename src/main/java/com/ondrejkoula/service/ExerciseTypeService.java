package com.ondrejkoula.service;

import com.ondrejkoula.domain.ExerciseType;
import com.ondrejkoula.repository.ExerciseTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ExerciseTypeService {

    private final ExerciseTypeRepository repository;

    @Autowired
    public ExerciseTypeService(ExerciseTypeRepository repository) {
        this.repository = repository;
    }

    public ExerciseType save(ExerciseType exerciseType) {
        log.info("Saving Exercise type: {}", exerciseType);
        ExerciseType save = repository.save(exerciseType);
        log.info("Exercise type successfully saved: {}", save);
        return save;
    }

    public ExerciseType findById(String id) {
        log.info("Getting Exercise Type with ID:  " + id);
        Optional<ExerciseType> exerciseType = repository.findById(id);
        if (!exerciseType.isPresent()) {
            log.info("Exercise Type with ID:  " + id + " not exists.");
            return null; // TODO throw NotFound exception
        }
        log.info("Exercise Type with ID:  " + id + " found.");
        return exerciseType.get();
    }

    public void deleteById(String id) {
        log.info("Deleting Exercise Type with ID:  " + id);
        repository.deleteById(id);
    }
}
