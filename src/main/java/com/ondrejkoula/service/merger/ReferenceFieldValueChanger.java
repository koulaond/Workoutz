package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.datachange.DataChange;
import com.ondrejkoula.exception.InconsistentDataFieldTypeOnUpdateException;
import com.ondrejkoula.exception.MissingDataForFieldUpdateException;

import java.lang.reflect.Field;

public class ReferenceFieldValueChanger extends FieldValueChanger {

    @Override
    protected <DE extends DomainEntity> void caseUpdate(DE target, Field field, DataChange changeForField) throws IllegalAccessException {
        Object newValue = changeForField.getValue();
        Object reference = field.get(target);

        validateAllDataAndTypesAreCorrect(target, field, newValue);

        if (reference instanceof DomainEntity) {
            ((DomainEntity) reference).setId(getNewValueAsLong(newValue));
        }
    }

    private Long getNewValueAsLong(Object newValue) {
        return ((Number) newValue).longValue();
    }

    private <DE extends DomainEntity> void validateAllDataAndTypesAreCorrect(DE target, Field field, Object newValue) {
        if (newValue == null) {
            throw new MissingDataForFieldUpdateException(target.getId(), field.getName());
        }
        if (!DomainEntity.class.isAssignableFrom(field.getType())) {
            throw new InconsistentDataFieldTypeOnUpdateException(target.getId(), field.getName(), field.getType(), newValue);
        }
        if (!(newValue instanceof Number)) {
            throw new InconsistentDataFieldTypeOnUpdateException(target.getId(), field.getName(), field.getType(), newValue);
        }
    }

    @Override
    protected <DE extends DomainEntity> void caseDelete(DE target, Field field) throws IllegalAccessException {
        field.set(target, null);
    }
}
