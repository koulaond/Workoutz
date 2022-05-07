package com.ondrejkoula.repository.exercise;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExercisePrescriptionRepository extends JpaRepository<ExercisePrescription, Long> {

    List<ExercisePrescription> findByExerciseTypeId(Long exerciseTypeId);
}
