package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.datachange.DataChange;
import com.ondrejkoula.dto.datachange.DataChanges;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@Component
public class ColumnFieldDataMerger implements DataMerger {


    @Override
    @SneakyThrows
    public <DE extends DomainEntity> void mergeSourceToTarget(DataChanges dataChanges, DE target) {
        Class<?> targetObjectClass = target.getClass();
        Map<String, DataChange> mapOfChanges = dataChanges.getChanges();
        FieldValueChangerFactory fieldValueChangerFactory = FieldValueChangerFactory.getFactory();

        while (targetObjectClass != null) {
            for (Field field : targetObjectClass.getDeclaredFields()) {
                fieldValueChangerFactory.getFieldValueChanger(field).setValueToField(target, mapOfChanges, field);
            }
            targetObjectClass = targetObjectClass.getSuperclass();
        }
    }
}
