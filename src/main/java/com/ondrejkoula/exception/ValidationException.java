package com.ondrejkoula.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private final String domainType;

    public ValidationException(String message, String domainType) {
        super(message);
        this.domainType = domainType;
    }

    public ValidationException(String message, Throwable cause, String domainType) {
        super(message, cause);
        this.domainType = domainType;
    }
}
