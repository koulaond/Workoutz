package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.repository.jpa.exercise.ExercisePrescriptionRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.dependencies.exercise.ExercisePrescriptionDependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExercisePrescriptionService extends GenericService<ExercisePrescription> {

    protected final ExercisePrescriptionRepository exercisePrescriptionRepository;

    @Autowired
    public ExercisePrescriptionService(ExercisePrescriptionRepository repository, ExercisePrescriptionDependencyService dependencyService) {
        super(repository, dependencyService);
        this.exercisePrescriptionRepository = repository;
    }

    public List<ExercisePrescription> findAllPrescriptionsForExerciseType(Long exerciseTypeId) {
        return exercisePrescriptionRepository.findByExerciseTypeId(exerciseTypeId);
    }
    
}
