package com.ondrejkoula.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnsupportedOperationException extends RuntimeException {

    private Long entityId;

    private String fieldName;

    private String operation;

    public UnsupportedOperationException(Long entityId, String fieldName, String operation) {
        this.entityId = entityId;
        this.fieldName = fieldName;
        this.operation = operation;
    }
}
