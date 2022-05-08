package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.dto.DataChanges;
import com.ondrejkoula.repository.exercise.ExerciseTypeRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.merger.DataMerger;
import com.ondrejkoula.service.validation.DataValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ExerciseTypeService extends GenericService<ExerciseType, ExerciseTypeRepository> {

    private final ExercisePrescriptionService exercisePrescriptionService;

    @Autowired
    public ExerciseTypeService(ExerciseTypeRepository repository, DataMerger dataMerger,
                               ExercisePrescriptionService exercisePrescriptionService) {
        super(repository, dataMerger, new ExerciseTypeDataValidator());
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

    private static class ExerciseTypeDataValidator extends DataValidator<ExerciseType> {


        @Override
        protected void doValidateOnCreate(ExerciseType toSave, Map<String, String> validationMessages) {

        }

        @Override
        protected void doValidateOnUpdate(DataChanges dataChanges, Map<String, String> validationMessages) {

        }
    }
}
