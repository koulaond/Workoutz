package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UnsupportedChangeTypeErrorDetailDto extends ErrorDetailDto{

    private final Long entityId;
    
    private final String fieldName;
    
    private final String operation;

    @Builder
    public UnsupportedChangeTypeErrorDetailDto(String errorMessage, String messageCode, Long entityId, 
                                               String fieldName, String operation) {
        super(errorMessage, messageCode);
        this.entityId = entityId;
        this.fieldName = fieldName;
        this.operation = operation;
    }
}
