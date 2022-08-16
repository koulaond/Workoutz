package com.ondrejkoula.service.validation;

import com.ondrejkoula.domain.plan.Plan;
import com.ondrejkoula.exception.validation.ValidationErrors;
import com.ondrejkoula.service.validation.annotation.Before;
import lombok.Builder;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

class DateFieldIsBeforeOtherValidatorTest {

    @Test
    void whenDatesSatisfyMinimumTimeWindow_thenNoErrorMessageIsLogged() throws NoSuchFieldException {
        Plan plan = Plan.builder().expectedPlanStart(now()).expectedPlanEnd(now().plusHours(4)).build();
        Field expectedPlanStartField = plan.getClass().getDeclaredField("expectedPlanStart");

        DateFieldIsBeforeOtherValidator validator = new DateFieldIsBeforeOtherValidator(expectedPlanStartField, plan);
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        assertThat(validationMessages).isEmpty();
    }

    @Test
    void whenDatesBreakMinimumTimeWindow_thenErrorMessageIsLogged() throws NoSuchFieldException {
        Plan plan = Plan.builder().expectedPlanStart(now()).expectedPlanEnd(now().plusMinutes(30)).build();
        Field expectedPlanStartField = plan.getClass().getDeclaredField("expectedPlanStart");

        DateFieldIsBeforeOtherValidator validator = new DateFieldIsBeforeOtherValidator(expectedPlanStartField, plan);
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        assertThat(validationMessages).hasSize(1).satisfies(map ->
                assertThat(map.get("expectedPlanStart")).isEqualTo(ValidationErrors.VALIDATION_FIELD_IS_NOT_BEFORE));
    }

    @Test
    void whenDatesTimeWindowIsReversed_thenErrorMessageIsLogged() throws NoSuchFieldException {
        Plan plan = Plan.builder().expectedPlanStart(now()).expectedPlanEnd(now().minusNanos(1)).build();
        Field expectedPlanStartField = plan.getClass().getDeclaredField("expectedPlanStart");

        DateFieldIsBeforeOtherValidator validator = new DateFieldIsBeforeOtherValidator(expectedPlanStartField, plan);
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        assertThat(validationMessages).hasSize(1).satisfies(map ->
                assertThat(map.get("expectedPlanStart")).isEqualTo(ValidationErrors.VALIDATION_FIELD_IS_NOT_BEFORE));
    }

    @Test
    void whenSourceFieldIsNotLocalDateTime_thenErrorMessageIsLogged() throws NoSuchFieldException {
        TestObjectThatBreaksTheValidation testObject = TestObjectThatBreaksTheValidation.builder()
                .fieldIsNotDateTime(LocalDate.now()).anotherField(now().plusMinutes(30)).build();

        Field expectedPlanStartField = testObject.getClass().getDeclaredField("fieldIsNotDateTime");

        DateFieldIsBeforeOtherValidator validator = new DateFieldIsBeforeOtherValidator(expectedPlanStartField, testObject);
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        assertThat(validationMessages).hasSize(1).satisfies(map ->
                assertThat(map.get("fieldIsNotDateTime")).isEqualTo(ValidationErrors.VALIDATION_FIELD_NOT_DATE_TIME_TYPE));
    }


    @Test
    void whenSourceFieldDoesNotExist_thenErrorMessageIsLogged() throws NoSuchFieldException {
        TestObjectThatBreaksTheValidation testObject = TestObjectThatBreaksTheValidation.builder()
                .fieldIsNotDateTime(LocalDate.now()).anotherField(now().plusMinutes(30)).build();

        Field expectedPlanStartField = Plan.class.getDeclaredField("expectedPlanStart");

        DateFieldIsBeforeOtherValidator validator = new DateFieldIsBeforeOtherValidator(expectedPlanStartField, testObject);
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        assertThat(validationMessages).hasSize(1).satisfies(map ->
                assertThat(map.get("expectedPlanEnd")).isEqualTo(ValidationErrors.VALIDATION_FIELD_NOT_DECLARED));
    }

    @Getter
    @Builder
    static class TestObjectThatBreaksTheValidation {

        @Before(otherFieldName = "anotherField", minimumHours = 1)
        private LocalDate fieldIsNotDateTime;

        private LocalDateTime anotherField;
    }
}