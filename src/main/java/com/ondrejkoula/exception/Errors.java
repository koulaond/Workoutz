package com.ondrejkoula.exception;

public final class Errors {

    public static final String VALIDATION_FIELD_NOT_DECLARED = "VALIDATION_FIELD_NOT_DECLARED";
    public static final String VALIDATION_FIELD_NOT_DATE_TIME_TYPE = "VALIDATION_FIELD_NOT_DATE_TIME_TYPE";
    public static final String VALIDATION_FIELD_NOT_ACCESSIBLE = "VALIDATION_FIELD_NOT_ACCESSIBLE";
    public static final String VALIDATION_MISSING_FIELD_CONTENT = "VALIDATION_MISSING_FIELD_CONTENT";
    public static final String VALIDATION_MISSING_REFERENCE = "VALIDATION_MISSING_REFERENCE";
    public static final String VALIDATION_REFERENCE_IS_NOT_DOMAIN_TYPE = "VALIDATION_REFERENCE_IS_NOT_DOMAIN_TYPE";
    public static final String VALIDATION_REFERENCE_ID_IS_MISSING = "VALIDATION_REFERENCE_ID_IS_MISSING";
    public static final String VALIDATION_REFERENCES_IS_NOT_COLLECTION = "VALIDATION_REFERENCES_IS_NOT_COLLECTION";

    public static final String VALIDATION_FIELD_IS_NOT_BEFORE = "VALIDATION_FIELD_IS_NOT_BEFORE";


    private Errors (){
        throw new java.lang.UnsupportedOperationException("No constructor for " + Error.class.getName());
    }
}
