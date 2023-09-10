package com.ondrejkoula.service.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.repository.jpa.exercise.ExercisePrescriptionRepository;
import com.ondrejkoula.repository.jpa.exercise.circle.SuperCircleSetExerciseRepository;
import com.ondrejkoula.service.dependencies.NoDependencyService;
import com.ondrejkoula.service.exercise.ExercisePrescriptionOwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperCircleSetExerciseService extends ExercisePrescriptionOwnerService<SuperCircleSetExercise> {

    @Autowired
    public SuperCircleSetExerciseService(SuperCircleSetExerciseRepository repository,
                                         NoDependencyService dependenciesCollector,
                                         ExercisePrescriptionRepository exercisePrescriptionRepository) {
        super(repository, dependenciesCollector, exercisePrescriptionRepository);
    }

}
