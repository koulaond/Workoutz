package com.ondrejkoula.repository;

import com.ondrejkoula.domain.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseTypeRepository extends JpaRepository<ExerciseType, String> {
}
