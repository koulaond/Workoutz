package com.ondrejkoula.service;

import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class WorkoutService {

    @Autowired
    private WorkoutRepository repository;

    @Transactional
    public Workout create(Workout workout) {
        return repository.save(workout);
    }
}
