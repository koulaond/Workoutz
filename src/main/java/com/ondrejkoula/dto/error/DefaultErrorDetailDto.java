package com.ondrejkoula.dto.error;

import lombok.Builder;

public class DefaultErrorDetailDto extends ErrorDetailDto{
    
    @Builder
    public DefaultErrorDetailDto(String errorMessage, String messageCode) {
        super(errorMessage, messageCode);
    }
}
