package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.repository.exercise.ExercisePrescriptionRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.merger.DataMerger;
import com.ondrejkoula.service.validation.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExercisePrescriptionService extends GenericService<ExercisePrescription, ExercisePrescriptionRepository> {

    @Autowired
    public ExercisePrescriptionService(ExercisePrescriptionRepository repository) {
        super(repository);
    }

    public List<ExercisePrescription> findAllPrescriptionsForExerciseType(Long exerciseTypeId) {
        return repository.findByExerciseTypeId(exerciseTypeId);
    }
}
