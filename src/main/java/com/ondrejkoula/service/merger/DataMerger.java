package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.DataChange;
import com.ondrejkoula.dto.DataChanges;
import com.ondrejkoula.exception.InconsistentDataUpdateException;
import com.ondrejkoula.exception.MissingDataForFieldException;
import com.ondrejkoula.exception.UnsupportedOperationException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
@Component
public class DataMerger {

    @SneakyThrows
    public <DE extends DomainEntity> void mergeSourceToTarget(DataChanges dataChanges, DE target) {

        Class<?> targetObjectClass = target.getClass();
        Map<String, DataChange> mapOfChanges = dataChanges.getChanges();

        while (targetObjectClass != null) {
            for (Field field : targetObjectClass.getDeclaredFields()) {
                if (!field.isAnnotationPresent(Column.class)) {
                    continue;
                }
                setValueToField(target, mapOfChanges, field);
            }
            targetObjectClass = targetObjectClass.getSuperclass();
        }
    }

    private <DE extends DomainEntity> void setValueToField(DE target, Map<String, DataChange> changes, Field field) throws IllegalAccessException {
        field.setAccessible(true);

        if (!changes.containsKey(field.getName())) {
            return;
        }

        DataChange changeForField = changes.get(field.getName());

        String changeType = getChangeType(changeForField);
        switch (changeType) {
            case "delete":
                field.set(target, null);
                break;

            case "update":
                Object newValue = changeForField.getValue();

                if (newValue == null) {
                    throw new MissingDataForFieldException(target.getId(), field.getName());
                }

                if (!field.getType().equals(newValue.getClass())) {
                    throw new InconsistentDataUpdateException(target.getId(), field.getName(), field.getType(), newValue);
                }

                field.set(target, newValue);
                break;
            default:
                throw new UnsupportedOperationException(target.getId(), field.getName(), changeType);
        }
    }

    private String getChangeType(DataChange changeForField) {
        String changeType = changeForField.getOperation();

        if (isBlank(changeType)) {
            return "update";
        }
        return changeType.toLowerCase();
    }
}
