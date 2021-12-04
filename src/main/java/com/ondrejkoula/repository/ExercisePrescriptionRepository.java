package com.ondrejkoula.repository;

import com.ondrejkoula.domain.ExercisePrescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisePrescriptionRepository extends JpaRepository<ExercisePrescription, Long> {
}
