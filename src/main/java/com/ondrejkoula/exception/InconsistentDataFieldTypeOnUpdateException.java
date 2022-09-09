package com.ondrejkoula.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InconsistentDataFieldTypeOnUpdateException extends RuntimeException {

    private Long entityId;

    private String fieldName;

    private Class<?> fieldType;

    private Object value;

    public InconsistentDataFieldTypeOnUpdateException(Long entityId, String fieldName, Class<?> fieldType, Object value) {
        this.entityId = entityId;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.value = value;
    }
}
