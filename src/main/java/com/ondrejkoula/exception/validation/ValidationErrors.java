package com.ondrejkoula.exception.validation;

public final class ValidationErrors {

    public static final String VALIDATION_FIELD_NOT_DECLARED = "FIELD_NOT_DECLARED";
    public static final String VALIDATION_FIELD_NOT_DATE_TIME_TYPE = "FIELD_NOT_DATE_TIME_TYPE";
    public static final String VALIDATION_FIELD_NOT_ACCESSIBLE = "FIELD_NOT_ACCESSIBLE";
    public static final String VALIDATION_MISSING_FIELD_CONTENT = "MISSING_FIELD_CONTENT";
    public static final String VALIDATION_MISSING_REFERENCE = "MISSING_REFERENCE";
    public static final String VALIDATION_REFERENCE_IS_NOT_DOMAIN_TYPE = "REFERENCE_IS_NOT_DOMAIN_TYPE";
    public static final String VALIDATION_REFERENCE_ID_IS_MISSING = "REFERENCE_ID_IS_MISSING";
    public static final String VALIDATION_REFERENCES_IS_NOT_COLLECTION = "REFERENCES_IS_NOT_COLLECTION";
    public static final String VALIDATION_FIELD_IS_NOT_BEFORE = "FIELD_IS_NOT_BEFORE";


    private ValidationErrors(){
        throw new java.lang.UnsupportedOperationException("No constructor for " + Error.class.getName());
    }
}
