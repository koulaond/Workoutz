package com.ondrejkoula.service.validation;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.exception.validation.ValidationErrors;

import java.util.Map;

import static java.util.Objects.isNull;

public class RequiredReferenceFieldValidator implements FieldValidator {

    private final String fieldName;

    private final Object fieldValue;

    public RequiredReferenceFieldValidator(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public void validateFieldValue(Map<String, String> validationMessages) {
        if (isNull(fieldValue)) {
            validationMessages.put(fieldName, ValidationErrors.VALIDATION_MISSING_REFERENCE);

        } else if (!DomainEntity.class.isAssignableFrom(fieldValue.getClass())) {
            validationMessages.put(fieldName, ValidationErrors.VALIDATION_REFERENCE_IS_NOT_DOMAIN_TYPE);

        } else if (isNull(((DomainEntity) fieldValue).getId())) {
            validationMessages.put(fieldName, ValidationErrors.VALIDATION_REFERENCE_ID_IS_MISSING);
        }
    }
}
