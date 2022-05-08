package com.ondrejkoula.exception;

public final class Errors {

    public static final String VALIDATION_MISSING_FIELD_CONTENT = "VALIDATION_MISSING_FIELD_CONTENT";
    public static final String VALIDATION_MISSING_REFERENCE = "VALIDATION_MISSING_REFERENCE";


    private Errors (){
        throw new java.lang.UnsupportedOperationException("No constructor for " + Error.class.getName());
    }
}
