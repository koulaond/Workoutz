package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.repository.jpa.exercise.ExercisePrescriptionRepository;
import com.ondrejkoula.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExercisePrescriptionService extends GenericService<ExercisePrescription> {

    protected final ExercisePrescriptionRepository exercisePrescriptionRepository;

    @Autowired
    public ExercisePrescriptionService(ExercisePrescriptionRepository repository) {
        super(repository);
        this.exercisePrescriptionRepository = repository;
    }

    public List<ExercisePrescription> findAllPrescriptionsForExerciseType(Long exerciseTypeId) {
        return exercisePrescriptionRepository.findByExerciseTypeId(exerciseTypeId);
    }
}
