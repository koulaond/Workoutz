package com.ondrejkoula.service.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.repository.jpa.exercise.circle.SuperCircleSetExerciseRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperCircleSetExerciseService extends GenericService<SuperCircleSetExercise> {

    @Autowired
    public SuperCircleSetExerciseService(SuperCircleSetExerciseRepository repository) {
        super(repository);
    }

}
