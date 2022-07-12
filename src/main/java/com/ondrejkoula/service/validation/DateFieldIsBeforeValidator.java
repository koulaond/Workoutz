package com.ondrejkoula.service.validation;

import com.ondrejkoula.exception.Errors;
import com.ondrejkoula.service.validation.annotation.Before;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
public class DateFieldIsBeforeValidator implements FieldValidator {

    private final Field field;

    private final Object fieldValue;

    private final Object fieldOwner;

    public DateFieldIsBeforeValidator(Field field, Object fieldValue, Object fieldOwner) {
        this.field = field;
        this.fieldValue = fieldValue;
        this.fieldOwner = fieldOwner;
    }

    @Override
    public void validateFieldValue(Map<String, String> validationMessages) {
        Before before = field.getAnnotation(Before.class);

        if (before == null) {
            log.warn("'Before' annotation is not defined on the field {}. Skipping validation", field.getName());
            return;
        }

        if (!(fieldValue instanceof LocalDateTime)) {
            log.warn("Field is not of type 'LocalDateTime'. Skipping validation...");
            return;
        }

        Field otherFieldDefinition = getOtherField(before.otherFieldName(), validationMessages);
        if (otherFieldDefinition == null) {
            return;
        }

        Object otherFieldValue = getFieldValue(otherFieldDefinition);
        if (!(otherFieldValue instanceof LocalDateTime)) {
            log.warn("Other field is not of type 'LocalDateTime'. Skipping validation...");
            return;
        }

        validateFieldIsBeforOtherField((LocalDateTime) fieldValue, (LocalDateTime) otherFieldValue,
                before.minimumHours(), validationMessages);
    }


    private Object getFieldValue(Field otherFieldDefinition) {
        try {
            return otherFieldDefinition.get(fieldOwner);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Field getOtherField(String otherFieldName, Map<String, String> validationMessages) {
        try {
            return fieldOwner.getClass().getField(otherFieldName);

        } catch (NoSuchFieldException e) {
            log.warn("Field {} not declared on class {}", otherFieldName, fieldOwner.getClass().getSimpleName());
            validationMessages.put(otherFieldName, Errors.VALIDATION_FIELD_NOT_DECLARED);
        }
        return null;
    }

    private void validateFieldIsBeforOtherField(LocalDateTime fieldValue, LocalDateTime otherFieldValue,
                                                int minimumHours, Map<String, String> validationMessages) {
            if (fieldValue.isAfter(otherFieldValue.minusHours(minimumHours))) {
                validationMessages.put(field.getName(), Errors.VALIDATION_FIELD_IS_NOT_BEFORE);
            }
    }
}
