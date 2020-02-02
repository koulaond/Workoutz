package com.ondrejkoula.service;

import com.ondrejkoula.domain.Exercise;
import com.ondrejkoula.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ExerciseService {

    @Autowired
    private ExerciseRepository repository;

    public Exercise create(Exercise exercise) {
        return repository.save(exercise);
    }

    public Optional<Exercise> findById(Long id) {
        return repository.findById(id);
    }

    public List<Exercise> findAll() {
        return repository.findAll();
    }

    public List<Exercise> findByName(String name) {
        return repository.findByNameStartingWith(name);
    }
}
