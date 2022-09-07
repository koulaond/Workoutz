package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.repository.jpa.exercise.ExerciseTypeRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.dependencies.exercise.ExerciseTypeDependencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExerciseTypeService extends GenericService<ExerciseType> {

    @Autowired
    public ExerciseTypeService(ExerciseTypeRepository repository,
                               ExerciseTypeDependencyService dependenciesCollector) {
        super(repository, dependenciesCollector);
    }

 
}
