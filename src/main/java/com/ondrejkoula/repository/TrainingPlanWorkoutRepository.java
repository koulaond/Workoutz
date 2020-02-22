package com.ondrejkoula.repository;

import com.ondrejkoula.domain.TrainingPlanWorkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingPlanWorkoutRepository extends JpaRepository<TrainingPlanWorkout, Long> {

    List<TrainingPlanWorkout> findByTrainingPlanId(Long trainingPlanId);
}
