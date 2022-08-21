package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InconsistentDataUpdateErrorDetailDto extends ErrorDetailDto {

    private final Long entityId;

    private final String fieldName;

    private final String expectedDataType;

    private final Object receivedValue;

    @Builder
    public InconsistentDataUpdateErrorDetailDto(String errorMessage, String messageCode, Long entityId,
                                                String fieldName, String expectedDataType, Object receivedValue) {
        super(errorMessage, messageCode);
        this.entityId = entityId;
        this.fieldName = fieldName;
        this.expectedDataType = expectedDataType;
        this.receivedValue = receivedValue;
    }
}
