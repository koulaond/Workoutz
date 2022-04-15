package com.ondrejkoula.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ValidationException extends InternalException {

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message, String messageCode) {
        super(message, messageCode);
    }

    public ValidationException(String message, String messageCode, Map<String, String> messageArguments) {
        super(message, messageCode, messageArguments);
    }
}
