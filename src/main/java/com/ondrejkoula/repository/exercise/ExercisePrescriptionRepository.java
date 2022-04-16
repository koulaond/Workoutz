package com.ondrejkoula.repository.exercise;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisePrescriptionRepository extends JpaRepository<ExercisePrescription, Long> {
}
