package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class ValidationErrorDetailDto extends ErrorDetailDto {
    
    private final Map<String, String> constraintViolations;

    @Builder
    public ValidationErrorDetailDto(String errorMessage, String messageCode, Map<String, String> constraintViolations) {
        super(errorMessage, messageCode);
        this.constraintViolations = constraintViolations;
    }
}
