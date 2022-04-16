package com.ondrejkoula.repository.exercise;

import com.ondrejkoula.domain.exercise.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
