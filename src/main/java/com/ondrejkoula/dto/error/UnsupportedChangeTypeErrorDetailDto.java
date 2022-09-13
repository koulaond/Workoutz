package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UnsupportedChangeTypeErrorDetailDto extends ErrorDetailDto{


    private final String operation;

    @Builder
    public UnsupportedChangeTypeErrorDetailDto(String errorMessage, String messageCode, String operation) {
        super(errorMessage, messageCode);
        this.operation = operation;
    }
}
