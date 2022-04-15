package com.ondrejkoula.service;

import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.repository.ExerciseTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExerciseTypeService extends GenericService<ExerciseType, ExerciseTypeRepository>{

    public ExerciseTypeService(ExerciseTypeRepository repository) {
        super(repository);
    }
}
