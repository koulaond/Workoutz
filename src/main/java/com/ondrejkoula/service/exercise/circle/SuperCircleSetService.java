package com.ondrejkoula.service.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircleSet;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.repository.jpa.exercise.circle.SuperCircleSetExerciseRepository;
import com.ondrejkoula.repository.jpa.exercise.circle.SuperCircleSetRepository;
import com.ondrejkoula.service.CompositionService;
import com.ondrejkoula.service.dependencies.NoDependenciesCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperCircleSetService extends CompositionService<SuperCircleSetExercise, SuperCircleSet> {

    public SuperCircleSetService(SuperCircleSetExerciseRepository childRepository,
                                 SuperCircleSetRepository parentRepository, 
                                 NoDependenciesCollector dependenciesCollector) {
        super(childRepository, parentRepository, dependenciesCollector);
    }
}
