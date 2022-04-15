package com.ondrejkoula.exception;

import java.util.Map;

public class ApiLimitException extends InternalException {

    public ApiLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiLimitException(String message, String messageCode) {
        super(message, messageCode);
    }

    public ApiLimitException(String message, String messageCode, Map<String, String> messageArguments) {
        super(message, messageCode, messageArguments);
    }
}
