package com.ondrejkoula.service;

import com.ondrejkoula.domain.Exercise;
import com.ondrejkoula.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExerciseService {

    @Autowired
    private ExerciseRepository repository;

    public Exercise save(Exercise exercise) {
        return repository.save(exercise);
    }

    public Exercise findById(Long id) {
        return repository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public List<Exercise> findAll() {
        return repository.findAll();
    }

    public List<Exercise> findByName(String name) {
        return repository.findByNameStartingWith(name);
    }
}
