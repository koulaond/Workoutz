package com.ondrejkoula.exception.validation;

import lombok.Getter;

import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {

    private final Map<String, String> constraintViolations;

    public ValidationException(Map<String, String> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }
}
