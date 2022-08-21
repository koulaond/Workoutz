package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class MissingDataOnSaveErrorDetailDto extends ErrorDetailDto{

    private final Map<String, String> errorMessages;

    @Builder
    public MissingDataOnSaveErrorDetailDto(String errorMessage, String messageCode, Map<String, String> errorMessages) {
        super(errorMessage, messageCode);
        this.errorMessages = errorMessages;
    }
}
