package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.datachange.DataChange;

import java.lang.reflect.Field;

public class ReferenceFieldValueChanger extends FieldValueChanger {

    @Override
    protected <DE extends DomainEntity> void caseUpdate(DE target, Field field, DataChange changeForField) throws IllegalAccessException {
        // Do nothing
    }

    @Override
    protected <DE extends DomainEntity> void caseDelete(DE target, Field field) throws IllegalAccessException {
        field.set(target, null);
    }
}
