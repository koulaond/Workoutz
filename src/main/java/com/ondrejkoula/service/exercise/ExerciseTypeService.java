package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.repository.exercise.ExerciseTypeRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.merger.DataMerger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExerciseTypeService extends GenericService<ExerciseType, ExerciseTypeRepository> {

    public ExerciseTypeService(ExerciseTypeRepository repository, DataMerger dataMerger) {
        super(repository, dataMerger);
    }
}
