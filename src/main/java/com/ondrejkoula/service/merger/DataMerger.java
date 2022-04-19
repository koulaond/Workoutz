package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.DomainEntity;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.lang.reflect.Field;

@Component
public abstract class DataMerger {

    @SneakyThrows
    public <DE extends DomainEntity> void mergeSourceToTarget(DE source, DE target) {

        if (!source.getClass().equals(target.getClass())) {
            throw new IllegalArgumentException(String.format("Cannot merge data from %s to %s",
                    source.getClass().getSimpleName(), target.getClass().getSimpleName()));
        }

        Class<?> c = source.getClass();
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {

                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    Object sourceFieldValue = field.get(source);

                    if (sourceFieldValue != null) {
                        field.set(sourceFieldValue, target);
                    }
                }
            }
            c = c.getSuperclass();
        }
    }
}
