package com.ondrejkoula.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnsupportedChangeTypeException extends RuntimeException {
    private Long entityId;
    private String fieldName;
    private String operation;

    public UnsupportedChangeTypeException(Long entityId, String fieldName, String operation) {
        this.entityId = entityId;
        this.fieldName = fieldName;
        this.operation = operation;
    }
}
