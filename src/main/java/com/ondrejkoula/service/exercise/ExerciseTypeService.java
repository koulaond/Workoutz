package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.repository.jpa.exercise.ExerciseTypeRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ExerciseTypeService extends GenericService<ExerciseType> {

    private final ExercisePrescriptionService exercisePrescriptionService;

    @Autowired
    public ExerciseTypeService(ExerciseTypeRepository repository, ExercisePrescriptionService exercisePrescriptionService) {
        super(repository);
        this.exercisePrescriptionService = exercisePrescriptionService;
    }


    @Override
    protected void doFindAllDependencies(Long id, Map<String, List<? extends DomainEntity>> allDependencies) {
        List<ExercisePrescription> allPrescriptionsForExerciseType
                = exercisePrescriptionService.findAllPrescriptionsForExerciseType(id);

        if (CollectionUtils.isNotEmpty(allPrescriptionsForExerciseType)) {
            allDependencies.put("ExercisePrescription", allPrescriptionsForExerciseType);
        }
    }
}
