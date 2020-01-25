package com.ondrejkoula.repository;

import com.ondrejkoula.domain.ExerciseType;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ExerciseTypeRepository extends Repository<ExerciseType, Long> {

    List<ExerciseType> findAll();

    ExerciseType findByType(String type);

    ExerciseType findById(Long id);
}
