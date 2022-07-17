package com.ondrejkoula.repository.jpa.exercise;

import com.ondrejkoula.domain.exercise.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseTypeRepository extends JpaRepository<ExerciseType, Long> {
}
