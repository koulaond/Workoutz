package com.ondrejkoula.service.dependencies.exercise;

import com.ondrejkoula.domain.EntityType;
import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.dto.Dependencies;
import com.ondrejkoula.service.dependencies.DependencyService;
import com.ondrejkoula.service.exercise.ExercisePrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExerciseTypeDependencyService extends DependencyService {

    private final ExercisePrescriptionService exercisePrescriptionService;

    @Autowired
    ExerciseTypeDependencyService(ExercisePrescriptionService exercisePrescriptionService) {
        this.exercisePrescriptionService = exercisePrescriptionService;
    }

    @Override
    public void doCollect(Long exerciseTypeId, List<Dependencies> dependenciesList) {
        List<ExercisePrescription> allPrescriptionsForExerciseType = exercisePrescriptionService.findAllPrescriptionsForExerciseType(exerciseTypeId);
        registerDependenciesForEntityType(EntityType.EXERCISE_PRESCRIPTION, allPrescriptionsForExerciseType, dependenciesList);
    }
}