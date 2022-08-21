package com.ondrejkoula.dto.error;

import lombok.Getter;

@Getter
public abstract class ErrorDetailDto {

    private final String errorMessage;
    
    private final String messageCode;
    
  
    public ErrorDetailDto(String errorMessage, String messageCode) {
        this.errorMessage = errorMessage;
        this.messageCode = messageCode;
    }
}
