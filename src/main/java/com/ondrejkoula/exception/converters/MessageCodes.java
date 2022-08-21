package com.ondrejkoula.exception.converters;

public final class MessageCodes {
    
    public static final String GENERAL_ERROR = "GENERAL_ERROR";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String INCONSISTENT_DATA = "INCONSISTENT_DATA";
    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    public static final String CASCADE_DEPENDENCIES = "CASCADE_DEPENDENCIES";
    public static final String INCONSISTENT_POSITIONS = "INCONSISTENT_POSITIONS";
    public static final String INCORRECT_PARENT = "INCORRECT_PARENT";
    public static final String MISSING_DATA_FOR_FIELD_UPDATE = "MISSING_DATA_FOR_FIELD_UPDATE";
    public static final String MISSING_DATA_ON_SAVE = "MISSING_DATA_ON_SAVE";
    public static final String OUT_OF_TIME_WINDOW = "OUT_OF_TIME_WINDOW";
    public static final String PARENT_NOT_FOUND = "PARENT_NOT_FOUND";
    public static final String POSITION_OUT_OF_RANGE = "POSITION_OUT_OF_RANGE";
    public static final String UNSUPPORTED_CHANGE_TYPE = "UNSUPPORTED_CHANGE_TYPE";
    public static final String UNSUPPORTED_COMPOSITE_CHANGE_TYPE = "UNSUPPORTED_COMPOSITE_CHANGE_TYPE";
    
    private MessageCodes() {
        throw new IllegalStateException("No instance of " + MessageCodes.class.getName());
    }
}
