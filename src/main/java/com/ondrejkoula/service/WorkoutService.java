package com.ondrejkoula.service;

import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkoutService {

    @Autowired
    private WorkoutRepository repository;

    public Workout create(Workout workout) {
        return repository.save(workout);
    }
}
