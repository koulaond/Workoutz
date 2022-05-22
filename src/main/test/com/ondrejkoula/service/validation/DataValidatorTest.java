package com.ondrejkoula.service.validation;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.dto.AbstractDTO;
import com.ondrejkoula.exception.MissingDataOnSaveException;
import com.ondrejkoula.service.validation.annotation.RequiredReference;
import lombok.Builder;
import lombok.Getter;
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
                .label("Label")
                .build();

        validator.validateAllMandatoryDataPresent(toValidate);
    }

    @Test
    void whenSomeRequiredFieldIsMissing_thenValidationExceptionIsThrown() {
        DataValidator validator = new DataValidator();
        ExercisePrescription toValidate = ExercisePrescription.builder()
                .id(1L)
                .status("Status")
                .note("Note")
                .exerciseType(ExerciseType.builder().id(2L).build())
                .description("Description")
                .build();

        assertThrows(MissingDataOnSaveException.class, () -> validator.validateAllMandatoryDataPresent(toValidate));
    }

    @Test
    void whenSomeRequiredFieldIsBlank_thenValidationExceptionIsThrown() {
        DataValidator validator = new DataValidator();
        ExercisePrescription toValidate = ExercisePrescription.builder()
                .id(1L)
                .status("Status")
                .note("Note")
                .exerciseType(ExerciseType.builder().id(2L).build())
                .description("Description")
                .label("")
                .build();

        assertThrows(MissingDataOnSaveException.class, () -> validator.validateAllMandatoryDataPresent(toValidate));
    }

    @Test
    void whenSomeRequiredReferenceIdIsMissing_thenValidationExceptionIsThrown() {
        DataValidator validator = new DataValidator();
        ExercisePrescription toValidate = ExercisePrescription.builder()
                .id(1L)
                .status("Status")
                .note("Note")
                .exerciseType(ExerciseType.builder().build())
                .description("Description")
                .label("Label")
                .build();

        assertThrows(MissingDataOnSaveException.class, () -> validator.validateAllMandatoryDataPresent(toValidate));
    }

    @Test
    void whenSomeRequiredReferenceIsMissing_thenValidationExceptionIsThrown() {
        DataValidator validator = new DataValidator();
        ExercisePrescription toValidate = ExercisePrescription.builder()
                .id(1L)
                .status("Status")
                .note("Note")
                .description("Description")
                .label("Label")
                .build();

        assertThrows(MissingDataOnSaveException.class, () -> validator.validateAllMandatoryDataPresent(toValidate));
    }

    @Test
    void whenSomeRequiredReferenceIsNotDomainType_thenValidationExceptionIsThrown() {
        DataValidator validator = new DataValidator();
        DomainEntityWithNonDomainReference toValidate = DomainEntityWithNonDomainReference.builder()
                .id(1L)
                .status("Status")
                .note("Note")
                .nonDomainObject(NonDomainObject.builder().value("Value").build())
                .build();

        assertThrows(MissingDataOnSaveException.class, () -> validator.validateAllMandatoryDataPresent(toValidate));
    }

    @Getter
    private static class DomainEntityWithNonDomainReference extends DomainEntity {

        @RequiredReference
        private final NonDomainObject nonDomainObject;

        @Builder
        public DomainEntityWithNonDomainReference(Long id, String status, String note, NonDomainObject nonDomainObject) {
            super(id, status, note);
            this.nonDomainObject = nonDomainObject;
        }

        @Override
        public AbstractDTO toDTO() {
            return null;
        }

        @Override
        public String loggableString() {
            return null;
        }
    }

    @Getter
    @Builder
    private static class NonDomainObject {
        private String value;
    }

}