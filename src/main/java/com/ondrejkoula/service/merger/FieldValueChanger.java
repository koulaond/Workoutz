package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.datachange.DataChange;
import com.ondrejkoula.exception.InternalException;

import java.lang.reflect.Field;
import java.util.Map;

public abstract class FieldValueChanger {

    <DE extends DomainEntity> void setValueToField(DE target, Map<String, DataChange> changes, Field field) {
        try {
            doSet(target, changes, field);
        } catch (IllegalAccessException e) {
            throw new InternalException(String.format("Could not set value to field %s.", field.getName()), e);
        }
    }
    <DE extends DomainEntity> void doSet(DE target, Map<String, DataChange> changes, Field field) throws IllegalAccessException {
        field.setAccessible(true);

        if (!changes.containsKey(field.getName())) {
            return;
        }

        DataChange changeForField = changes.get(field.getName());

        ChangeType changeType = ChangeType.fromString(changeForField.getOperation());
        switch (changeType) {
            case DELETE:
                caseDelete(target, field);
                break;

            case UPDATE:
                caseUpdate(target, field, changeForField);
                break;
        }
    }

    protected abstract  <DE extends DomainEntity> void caseUpdate(DE target, Field field, DataChange changeForField) throws IllegalAccessException;

    protected abstract  <DE extends DomainEntity> void caseDelete(DE target, Field field) throws IllegalAccessException;

}
