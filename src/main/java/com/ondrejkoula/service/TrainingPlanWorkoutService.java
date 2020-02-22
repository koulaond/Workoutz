package com.ondrejkoula.service;

import com.ondrejkoula.domain.TrainingPlanWorkout;
import com.ondrejkoula.repository.TrainingPlanWorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TrainingPlanWorkoutService {

    @Autowired
    private TrainingPlanWorkoutRepository repository;

    public TrainingPlanWorkout save(TrainingPlanWorkout trainingPlanWorkout) {
        return repository.save(trainingPlanWorkout);
    }

    public List<TrainingPlanWorkout> findByTrainingPlanId(Long trainingPlanId) {
        return repository.findByTrainingPlanId(trainingPlanId);
    }
}
