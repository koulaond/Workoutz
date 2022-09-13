package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.datachange.DataChange;
import com.ondrejkoula.exception.InconsistentDataFieldTypeOnUpdateException;
import com.ondrejkoula.exception.MissingDataForFieldUpdateException;

import java.lang.reflect.Field;

public class PrimitiveFieldValueChanger extends FieldValueChanger {
    
    @Override
    protected  <DE extends DomainEntity> void caseUpdate(DE target, Field field, DataChange changeForField) throws IllegalAccessException {
        Object newValue = changeForField.getValue();

        if (newValue == null) {
            throw new MissingDataForFieldUpdateException(target.getId(), field.getName());
        }

        if (!field.getType().equals(newValue.getClass())) {
            throw new InconsistentDataFieldTypeOnUpdateException(target.getId(), field.getName(), field.getType(), newValue);
        }

        field.set(target, newValue);
    }

    @Override
    protected  <DE extends DomainEntity> void caseDelete(DE target, Field field) throws IllegalAccessException {
        field.set(target, null);
    }
}
