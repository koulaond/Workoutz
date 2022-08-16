package com.ondrejkoula.dto.error;

import com.ondrejkoula.exception.converters.MessageCodes;
import com.ondrejkoula.exception.validation.ValidationException;

public class ErrorDetailDtoFactory {
    
    public static ErrorDetailDto fromGeneralError(Exception ex) {
        return ErrorDetailDto.builder().errorMessage(ex.getMessage()).messageCode(MessageCodes.GENERAL_ERROR).build();
    }
    
    public static ValidationErrorDetailDto fromValidationError(ValidationException valEx) {
        return ValidationErrorDetailDto.builder()
                .errorMessage("Validation constraints violated.")
                .messageCode(MessageCodes.VALIDATION_ERROR)
                .constraintViolations(valEx.getConstraintViolations())
                .build();
    }
}
