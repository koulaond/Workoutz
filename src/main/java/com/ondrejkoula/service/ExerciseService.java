package com.ondrejkoula.service;

import com.ondrejkoula.domain.Exercise;
import com.ondrejkoula.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ExerciseService {

    @Autowired
    private ExerciseRepository repository;

    public Exercise createExercise(Exercise exercise) {
        return repository.save(exercise);
    }
}
