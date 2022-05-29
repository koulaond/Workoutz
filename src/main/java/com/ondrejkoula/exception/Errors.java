package com.ondrejkoula.exception;

public final class Errors {

    public static final String VALIDATION_MISSING_FIELD_CONTENT = "VALIDATION_MISSING_FIELD_CONTENT";
    public static final String VALIDATION_MISSING_REFERENCE = "VALIDATION_MISSING_REFERENCE";
    public static final String VALIDATION_REFERENCE_IS_NOT_DOMAIN_TYPE = "VALIDATION_MISSING_REFERENCE";
    public static final String VALIDATION_REFERENCE_ID_IS_MISSING = "VALIDATION_REFERENCE_ID_IS_MISSING";

    public static final String VALIDATION_REFERENCES_IS_NOT_COLLECTION = "VALIDATION_REFERENCES_IS_NOT_COLLECTION";


    private Errors (){
        throw new java.lang.UnsupportedOperationException("No constructor for " + Error.class.getName());
    }
}
