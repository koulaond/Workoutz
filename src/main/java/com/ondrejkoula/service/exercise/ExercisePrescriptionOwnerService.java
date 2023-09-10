package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.domain.exercise.ExercisePrescriptionOwner;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.repository.jpa.exercise.ExercisePrescriptionRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.dependencies.DependencyService;
import org.springframework.data.jpa.repository.JpaRepository;

import static java.util.Collections.singletonMap;


public abstract class ExercisePrescriptionOwnerService<EPO extends ExercisePrescriptionOwner> extends GenericService<EPO> {

    private final ExercisePrescriptionRepository exercisePrescriptionRepository;

    public ExercisePrescriptionOwnerService(JpaRepository<EPO, Long> repository,
                                            DependencyService dependencyService,
                                            ExercisePrescriptionRepository exercisePrescriptionRepository) {
        super(repository, dependencyService);
        this.exercisePrescriptionRepository = exercisePrescriptionRepository;
    }

    public EPO changeExercisePrescription(Long parentId, Long newExercisePrescriptionId) {
        EPO parent = findById(parentId);

        ExercisePrescription foundExercisePrescription = exercisePrescriptionRepository.findById(newExercisePrescriptionId)
                .orElseThrow(() -> new DataNotFoundException("Exercise prescription not found", "dataNotFound",
                        singletonMap("exercisePrescriptionId", newExercisePrescriptionId.toString())));
        
        parent.setExercisePrescription(foundExercisePrescription);
        return repository.save(parent);
    }

}
