package com.ondrejkoula.service.validation;

import com.ondrejkoula.exception.Errors;
import com.ondrejkoula.service.validation.annotation.Before;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
public class DateFieldIsBeforeOtherValidator implements FieldValidator {

    private final Field field;

    private final Object fieldOwner;

    public DateFieldIsBeforeOtherValidator(Field field, Object fieldOwner) {
        this.field = field;
        this.fieldOwner = fieldOwner;
    }

    @Override
    public void validateFieldValue(Map<String, String> validationMessages) {
        Before before = field.getAnnotation(Before.class);

        if (before == null) {
            log.warn("'Before' annotation is not defined on the field {}. Skipping validation", field.getName());
            return;
        }

        try {
            doValidation(before.otherFieldName(), before.minimumHours(), validationMessages);
        } catch (Exception e) {
            log.warn("Validation failed due to internal error: ", e);
        }
    }

    private void doValidation(String otherFieldName, Integer minimumHours, Map<String, String> validationMessages)
            throws IllegalAccessException, NoSuchFieldException {

        Field otherFieldDefinition = tryGetFieldDefinitionFromClass(otherFieldName, validationMessages);

        LocalDateTime fieldValue = tryGetFieldValueAsDate(field, validationMessages);
        LocalDateTime otherFieldValue = tryGetFieldValueAsDate(otherFieldDefinition, validationMessages);
        validateFieldIsBeforeOtherField(fieldValue, otherFieldValue, minimumHours, validationMessages);
    }

    private LocalDateTime tryGetFieldValueAsDate(Field fieldDefinition, Map<String, String> validationMessages) throws IllegalAccessException {
        try {
            fieldDefinition.setAccessible(true);
            return (LocalDateTime) fieldDefinition.get(fieldOwner);

        } catch(ClassCastException e){
            validationMessages.put(fieldDefinition.getName(), Errors.VALIDATION_FIELD_NOT_DATE_TIME_TYPE);
            throw e;
        }
        catch (IllegalAccessException e) {
            validationMessages.put(fieldDefinition.getName(), Errors.VALIDATION_FIELD_NOT_ACCESSIBLE);
            throw e;
        }
    }

    private Field tryGetFieldDefinitionFromClass(String fieldName, Map<String, String> validationMessages) throws NoSuchFieldException {
        try {
            return fieldOwner.getClass().getDeclaredField(fieldName);

        } catch (NoSuchFieldException e) {
            validationMessages.put(fieldName, Errors.VALIDATION_FIELD_NOT_DECLARED);
            throw e;
        }
    }

    private void validateFieldIsBeforeOtherField(LocalDateTime fieldValue, LocalDateTime otherFieldValue,
                                                 int minimumHours, Map<String, String> validationMessages) {
        if (fieldValue.isAfter(otherFieldValue.minusHours(minimumHours))) {
            validationMessages.put(field.getName(), Errors.VALIDATION_FIELD_IS_NOT_BEFORE);
        }
    }
}
