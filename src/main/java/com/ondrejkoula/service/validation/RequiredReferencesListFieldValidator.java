package com.ondrejkoula.service.validation;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.exception.validation.ValidationErrors;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static java.util.Objects.isNull;

public class RequiredReferencesListFieldValidator implements FieldValidator {
    private final String fieldName;

    private final Object fieldValue;

    public RequiredReferencesListFieldValidator(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public void validateFieldValue(Map<String, String> validationMessages) {
        if (isNull(fieldValue)) {
            validationMessages.put(fieldName, ValidationErrors.VALIDATION_MISSING_REFERENCE);

        } else if (!(fieldValue instanceof Collection<?>)) {
            validationMessages.put(fieldName, ValidationErrors.VALIDATION_REFERENCES_IS_NOT_COLLECTION);

        } else {
            validateEachItemInCollection(validationMessages);
        }
    }

    private void validateEachItemInCollection(Map<String, String> validationMessages) {
        Collection<?> items = (Collection<?>) fieldValue;
        Iterator<?> iterator = items.iterator();

        int index = 0;
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (!DomainEntity.class.isAssignableFrom(next.getClass())) {
                validationMessages.put(fieldName + '[' + index + ']', ValidationErrors.VALIDATION_REFERENCE_IS_NOT_DOMAIN_TYPE);

            } else if (isNull(((DomainEntity) next).getId())) {
                validationMessages.put(fieldName + '[' + index + ']', ValidationErrors.VALIDATION_REFERENCE_ID_IS_MISSING);
            }
            index++;
        }
    }
}
