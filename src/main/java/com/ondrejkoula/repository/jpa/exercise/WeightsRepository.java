package com.ondrejkoula.repository.jpa.exercise;

import com.ondrejkoula.domain.exercise.weights.Weights;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightsRepository extends JpaRepository<Weights, Long> {
    
    List<Weights> findByExercisePrescriptionId(Long exercisePrescriptionId);
}
