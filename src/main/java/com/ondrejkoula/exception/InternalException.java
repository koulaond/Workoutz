package com.ondrejkoula.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class InternalException extends RuntimeException {

    private static final String UNKNOWN_HTTP_STATUS_CODE = "error.unknownHttpStatusCodeReturned";

    private final String messageCode;

    private final Map<String, String> messageArguments;

    public InternalException(String message, String messageCode) {
        super(message);
        this.messageCode = messageCode;
        this.messageArguments = null;
    }

    public InternalException(String message, String messageCode, Map<String, String> messageArguments) {
        super(message);
        this.messageCode = messageCode;
        this.messageArguments = messageArguments;
    }

}
