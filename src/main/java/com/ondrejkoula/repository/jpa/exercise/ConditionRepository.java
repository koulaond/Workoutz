package com.ondrejkoula.repository.jpa.exercise;

import com.ondrejkoula.domain.exercise.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConditionRepository extends JpaRepository<Condition, Long> {

    List<Condition> findByExercisePrescriptionId(Long exercisePrescriptionId);
}
