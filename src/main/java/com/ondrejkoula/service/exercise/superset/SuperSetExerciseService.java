package com.ondrejkoula.service.exercise.superset;

import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.repository.jpa.exercise.ExercisePrescriptionRepository;
import com.ondrejkoula.repository.jpa.exercise.superset.SuperSetExerciseRepository;
import com.ondrejkoula.service.dependencies.NoDependencyService;
import com.ondrejkoula.service.exercise.ExercisePrescriptionOwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperSetExerciseService extends ExercisePrescriptionOwnerService<SuperSetExercise> {

    @Autowired
    public SuperSetExerciseService(SuperSetExerciseRepository repository,
                                   NoDependencyService dependenciesCollector, 
                                   ExercisePrescriptionRepository exercisePrescriptionRepository) {
        super(repository, dependenciesCollector, exercisePrescriptionRepository);
    }
}
