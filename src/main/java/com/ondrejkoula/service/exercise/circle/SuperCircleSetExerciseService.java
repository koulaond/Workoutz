package com.ondrejkoula.service.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircleSet;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.repository.exercise.circle.SuperCircleSetExerciseRepository;
import com.ondrejkoula.repository.exercise.circle.SuperCircleSetRepository;
import com.ondrejkoula.service.IncorporatedItemService;
import com.ondrejkoula.service.merger.DataMerger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperCircleSetExerciseService extends IncorporatedItemService<SuperCircleSetExercise, SuperCircleSetExerciseRepository, SuperCircleSet, SuperCircleSetRepository> {

    @Autowired
    public SuperCircleSetExerciseService(SuperCircleSetExerciseRepository repository, SuperCircleSetRepository parentRepository) {
        super(repository, parentRepository);
    }

}
