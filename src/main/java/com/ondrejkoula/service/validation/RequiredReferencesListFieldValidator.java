package com.ondrejkoula.service.validation;

import java.util.Map;

public class RequiredReferencesListFieldValidator implements FieldValidator{
    private final String fieldName;

    private final Object fieldValue;

    public RequiredReferencesListFieldValidator(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    @Override
    public void validateFieldValue(Map<String, String> validationMessages) {
        // TODO
    }
}
