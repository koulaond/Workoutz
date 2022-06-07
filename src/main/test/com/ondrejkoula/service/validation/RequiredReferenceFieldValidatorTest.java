package com.ondrejkoula.service.validation;

import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.domain.workout.WorkoutExercise;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.ondrejkoula.exception.Errors.*;
import static org.junit.jupiter.api.Assertions.*;

class RequiredReferenceFieldValidatorTest {

    @Test
    void whenFieldValueIsNull_thenPutErrorToMessages() {
        RequiredReferenceFieldValidator validator = new RequiredReferenceFieldValidator("field", null);
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        Assertions.assertThat(validationMessages).contains(Map.entry("field", VALIDATION_MISSING_REFERENCE));
    }

    @Test
    void whenFieldValueIsNotDomainType_thenPutErrorToMessages() {
        RequiredReferenceFieldValidator validator = new RequiredReferenceFieldValidator("field", new WorkoutExercise());
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        Assertions.assertThat(validationMessages).contains(Map.entry("field", VALIDATION_REFERENCE_IS_NOT_DOMAIN_TYPE));
    }

    @Test
    void whenFieldValueIsDomainTypeButMissingId_thenPutErrorToMessages() {
        RequiredReferenceFieldValidator validator = new RequiredReferenceFieldValidator("field", new Workout());
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        Assertions.assertThat(validationMessages).contains(Map.entry("field", VALIDATION_REFERENCE_ID_IS_MISSING));
    }

    @Test
    void whenFieldValueIsDomainTypeAndHasId_thenDoNotPutErrorToMessages() {
        RequiredReferenceFieldValidator validator = new RequiredReferenceFieldValidator("field", Workout.builder().id(1L).build());
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        Assertions.assertThat(validationMessages).isEmpty();
    }
}