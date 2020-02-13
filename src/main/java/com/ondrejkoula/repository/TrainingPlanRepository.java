package com.ondrejkoula.repository;

import com.ondrejkoula.domain.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Long> {
}
