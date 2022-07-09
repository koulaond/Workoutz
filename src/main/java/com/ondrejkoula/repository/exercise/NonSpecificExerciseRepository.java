package com.ondrejkoula.repository.exercise;

import com.ondrejkoula.domain.exercise.Exercise;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface NonSpecificExerciseRepository extends Repository<Exercise, Long> {

    Optional<Exercise> findById(Long id);
}
