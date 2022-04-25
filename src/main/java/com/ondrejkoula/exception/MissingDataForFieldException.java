package com.ondrejkoula.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissingDataForFieldException extends RuntimeException{

    private Long entityId;

    private String fieldName;

    public MissingDataForFieldException(Long entityId, String fieldName) {
        this.entityId = entityId;
        this.fieldName = fieldName;
    }
}
