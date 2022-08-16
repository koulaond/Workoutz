package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Data;

@Data
public class ErrorDetailDto {

    private String errorMessage;
    
    private String messageCode;
    

    @Builder
    public ErrorDetailDto(String errorMessage, String messageCode) {
        this.errorMessage = errorMessage;
        this.messageCode = messageCode;
    }
}
