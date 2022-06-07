package com.ondrejkoula.service.validation;

import com.ondrejkoula.exception.Errors;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class RequiredFieldValidator implements FieldValidator {

    private final String fieldName;

    private final Object fieldValue;

    public RequiredFieldValidator(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public void validateFieldValue(Map<String, String> validationMessages) {
        if (isNull(fieldValue) || isBlank(fieldValue.toString())) {
            validationMessages.put(fieldName, Errors.VALIDATION_MISSING_FIELD_CONTENT);
        }

    }
}
