package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MissingDataForFieldUpdateErrorDetailDto extends ErrorDetailDto {

    private final Long entityId;

    private final String fieldName;

    @Builder
    public MissingDataForFieldUpdateErrorDetailDto(String errorMessage, String messageCode, Long entityId, String fieldName) {
        super(errorMessage, messageCode);
        this.entityId = entityId;
        this.fieldName = fieldName;
    }
}
