package com.ondrejkoula.service.dependencies.exercise;

import com.ondrejkoula.domain.EntityType;
import com.ondrejkoula.domain.exercise.Condition;
import com.ondrejkoula.domain.exercise.HighIntensityInterval;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.domain.exercise.weights.Weights;
import com.ondrejkoula.dto.Dependencies;
import com.ondrejkoula.repository.jpa.exercise.ConditionRepository;
import com.ondrejkoula.repository.jpa.exercise.HighIntensityIntervalRepository;
import com.ondrejkoula.repository.jpa.exercise.WeightsRepository;
import com.ondrejkoula.repository.jpa.exercise.circle.SuperCircleSetExerciseRepository;
import com.ondrejkoula.repository.jpa.exercise.superset.SuperSetExerciseRepository;
import com.ondrejkoula.service.dependencies.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExercisePrescriptionDependencyService extends DependencyService {
    
    private final WeightsRepository weightsRepository;
    
    private final HighIntensityIntervalRepository highIntensityIntervalRepository;
    
    private final ConditionRepository conditionRepository;
    
    private final SuperCircleSetExerciseRepository superCircleSetExerciseRepository;
    
    private final SuperSetExerciseRepository superSetExerciseRepository;

    @Autowired
    public ExercisePrescriptionDependencyService(WeightsRepository weightsRepository,
                                                 HighIntensityIntervalRepository highIntensityIntervalRepository,
                                                 ConditionRepository conditionRepository,
                                                 SuperCircleSetExerciseRepository superCircleSetExerciseRepository, 
                                                 SuperSetExerciseRepository superSetExerciseRepository) {
        this.weightsRepository = weightsRepository;
        this.highIntensityIntervalRepository = highIntensityIntervalRepository;
        this.conditionRepository = conditionRepository;
        this.superCircleSetExerciseRepository = superCircleSetExerciseRepository;
        this.superSetExerciseRepository = superSetExerciseRepository;
    }

    @Override
    public void doCollect(Long exercisePrescriptionId, List<Dependencies> dependenciesList) {
        List<Weights> weightsList = weightsRepository.findByExercisePrescriptionId(exercisePrescriptionId);
        registerDependenciesForEntityType(EntityType.WEIGHTS, weightsList, dependenciesList);

        List<HighIntensityInterval> hiitList = highIntensityIntervalRepository.findByExercisePrescriptionId(exercisePrescriptionId);
        registerDependenciesForEntityType(EntityType.HIGH_INTENSITY_INTERVAL, hiitList, dependenciesList);

        List<Condition> conditionList = conditionRepository.findByExercisePrescriptionId(exercisePrescriptionId);
        registerDependenciesForEntityType(EntityType.CONDITION, conditionList, dependenciesList);

        List<SuperCircleSetExercise> superCircleSetExerciseList = superCircleSetExerciseRepository.findByExercisePrescriptionId(exercisePrescriptionId);
        registerDependenciesForEntityType(EntityType.SUPER_CIRCLE_SET_EXERCISE, superCircleSetExerciseList, dependenciesList);

        List<SuperSetExercise> superSetExerciseList = superSetExerciseRepository.findByExercisePrescriptionId(exercisePrescriptionId);
        registerDependenciesForEntityType(EntityType.SUPER_SET_EXERCISE, superSetExerciseList, dependenciesList);
    }
}
