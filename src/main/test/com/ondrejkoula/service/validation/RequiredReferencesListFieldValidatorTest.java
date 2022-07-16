package com.ondrejkoula.service.validation;

import com.ondrejkoula.domain.exercise.circle.SuperCircle;
import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.domain.workout.ExerciseToWorkoutAssignment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ondrejkoula.exception.Errors.*;

class RequiredReferencesListFieldValidatorTest {


    @Test
    void whenFieldValueIsNull_thenPutErrorToMessages() {
        RequiredReferencesListFieldValidator validator = new RequiredReferencesListFieldValidator("field", null);
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        Assertions.assertThat(validationMessages).contains(Map.entry("field", VALIDATION_MISSING_REFERENCE));
    }

    @Test
    void whenFieldValueIsNotCollection_thenPutErrorToMessages() {
        RequiredReferencesListFieldValidator validator = new RequiredReferencesListFieldValidator("field", Workout.builder().build());
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        Assertions.assertThat(validationMessages).contains(Map.entry("field", VALIDATION_REFERENCES_IS_NOT_COLLECTION));
    }

    @Test
    void whenCollectionContainsNonDomainTypeAndTypeWithoutId_thenPutTwoErrorToMessages() {
        List<?> fieldValue = Arrays.asList(
                new ExerciseToWorkoutAssignment(),
                Workout.builder().build(),
                SuperCircle.builder().id(1L).build()

        );
        RequiredReferencesListFieldValidator validator = new RequiredReferencesListFieldValidator("field", fieldValue);
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        Assertions.assertThat(validationMessages)
                .hasSize(2)
                .contains(Map.entry("field[0]", VALIDATION_REFERENCE_IS_NOT_DOMAIN_TYPE))
                .contains(Map.entry("field[1]", VALIDATION_REFERENCE_ID_IS_MISSING));
    }
}