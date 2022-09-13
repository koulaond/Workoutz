package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.datachange.DataChange;

import java.lang.reflect.Field;
import java.util.Map;

public class NoFieldValueChanger extends FieldValueChanger {
   
    @Override
    <DE extends DomainEntity> void setValueToField(DE target, Map<String, DataChange> changes, Field field) {
        // Do nothing
    }

    @Override
    protected <DE extends DomainEntity> void caseUpdate(DE target, Field field, DataChange changeForField) {
        // Do nothing
    }

    @Override
    protected <DE extends DomainEntity> void caseDelete(DE target, Field field) {
        // Do nothing
    }
}
