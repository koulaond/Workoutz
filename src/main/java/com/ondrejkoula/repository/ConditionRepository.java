package com.ondrejkoula.repository;

import com.ondrejkoula.domain.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
