package com.ondrejkoula.service.validation;

import com.ondrejkoula.exception.Errors;

import java.util.Map;
import java.util.Objects;

public class RequiredReferenceFieldValidator implements FieldValidator {

    private final String fieldName;

    private final Object fieldValue;

    public RequiredReferenceFieldValidator(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public void validateFieldValue(Map<String, String> validationMessages) {
        if (Objects.isNull(fieldValue)) {
            validationMessages.put(fieldName, Errors.VALIDATION_MISSING_REFERENCE);
        }
    }
}
