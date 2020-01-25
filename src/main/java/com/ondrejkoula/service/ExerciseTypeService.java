package com.ondrejkoula.service;

import com.ondrejkoula.domain.ExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import com.ondrejkoula.repository.ExerciseTypeRepository;

import java.util.List;

public class ExerciseTypeService {

    @Autowired
    private ExerciseTypeRepository repository;

    public List<ExerciseType> findAll()  {
        return repository.findAll();
    }

    public ExerciseType findByType(String type) {
        return repository.findByType(type);
    }

    public ExerciseType findById(Long id) {
        return repository.findById(id);
    }
}
