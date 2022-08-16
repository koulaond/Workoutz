package com.ondrejkoula.exception;

import java.util.Map;

public class DataNotFoundException extends InternalException {

    public DataNotFoundException(String message, String messageCode) {
        super(message, messageCode);
    }
    
    public DataNotFoundException(String message, String messageCode, Map<String, String> messageArguments) {
        super(message, messageCode, messageArguments);
    }
}
