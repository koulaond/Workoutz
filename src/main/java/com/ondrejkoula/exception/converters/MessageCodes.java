package com.ondrejkoula.exception.converters;

public final class MessageCodes {
    
    public static final String GENERAL_ERROR = "GENERAL_ERROR";
    
    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    
    private MessageCodes() {
        throw new IllegalStateException("No instance of " + MessageCodes.class.getName());
    }
}
