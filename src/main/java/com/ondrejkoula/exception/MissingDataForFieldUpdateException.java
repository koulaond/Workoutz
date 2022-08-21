package com.ondrejkoula.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissingDataForFieldUpdateException extends RuntimeException{

    private Long entityId;

    private String fieldName;

    public MissingDataForFieldUpdateException(Long entityId, String fieldName) {
        this.entityId = entityId;
        this.fieldName = fieldName;
    }
}
