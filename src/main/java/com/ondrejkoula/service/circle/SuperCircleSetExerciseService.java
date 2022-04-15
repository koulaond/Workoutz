package com.ondrejkoula.service.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircleSet;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.repository.circle.SuperCircleSetExerciseRepository;
import com.ondrejkoula.repository.circle.SuperCircleSetRepository;
import com.ondrejkoula.service.IncorporatedItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperCircleSetExerciseService extends IncorporatedItemService<SuperCircleSetExercise, SuperCircleSetExerciseRepository, SuperCircleSet, SuperCircleSetRepository> {

    public SuperCircleSetExerciseService(SuperCircleSetExerciseRepository repository, SuperCircleSetRepository parentRepository) {
        super(repository, parentRepository);
    }

}
