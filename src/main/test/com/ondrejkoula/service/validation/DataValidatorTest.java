package com.ondrejkoula.service.validation;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.domain.exercise.ExerciseType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataValidatorTest {

    @Test
    void whenAllRequiredDataIsPresent_thenNoValidationErrorMessageIsThrown() {
        DataValidator validator = new DataValidator();
        ExercisePrescription toValidate = ExercisePrescription.builder()
                .id(1L)
                .status("Status")
                .note("Note")
                .exerciseType(ExerciseType.builder().id(2L).build())
                .description("Description")
                .build();

        validator.validateAllMandatoryDataPresent(toValidate);
    }
}