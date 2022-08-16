package com.ondrejkoula.dto.error;

import lombok.Data;

import java.util.List;

@Data
public class ErrorDetailsDto {
    
    private List<ErrorDetailDto> errors;
}
