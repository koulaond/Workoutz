package com.ondrejkoula.repository;

import com.ondrejkoula.domain.exercise.Exercise;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ExerciseRepository extends Repository<Exercise, Long> {

    Optional<Exercise> findById(Long id);
}
