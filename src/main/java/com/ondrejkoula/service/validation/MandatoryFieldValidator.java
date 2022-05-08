package com.ondrejkoula.service.validation;

import com.ondrejkoula.exception.Errors;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

public class MandatoryFieldValidator implements FieldValidator {

    private final String fieldName;

    private final Object fieldValue;

    public MandatoryFieldValidator(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public void validateFieldValue(Map<String, String> validationMessages) {
        if (Objects.isNull(fieldValue) || StringUtils.isBlank(fieldValue.toString())) {
            validationMessages.put(fieldName, Errors.VALIDATION_MISSING_FIELD_CONTENT);
        }

    }
}
