package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.DataChange;
import com.ondrejkoula.dto.DataChanges;
import com.ondrejkoula.exception.InconsistentDataUpdateException;
import com.ondrejkoula.exception.MissingDataForFieldException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@Component
public class DataMerger {

    @SneakyThrows
    public <DE extends DomainEntity> void mergeSourceToTarget(DataChanges dataChanges, DE target) {

        Class<?> c = target.getClass();
        Map<String, DataChange> changes = dataChanges.getChanges();

        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (!field.isAnnotationPresent(Column.class)) {
                    continue;
                }
                setValueToField(target, changes, field);
            }
            c = c.getSuperclass();
        }
    }

    private <DE extends DomainEntity> void setValueToField(DE target, Map<String, DataChange> changes, Field field) throws IllegalAccessException {
        field.setAccessible(true);

        if (!changes.containsKey(field.getName())) {
            return;
        }

        DataChange changeForField = changes.get(field.getName());

        switch (changeForField.getChangeType()) {
            case DELETE:
                field.set(target, null);
                break;

            case UPDATE:
                Object newValue = changeForField.getValue();

                if (newValue == null) {
                    throw new MissingDataForFieldException(target.getId(), field.getName());
                }

                if (!field.getType().equals(newValue.getClass())) {
                    throw new InconsistentDataUpdateException(target.getId(), field.getName(), field.getType(), newValue);
                }

                field.set(target, newValue);

        }
    }
}
