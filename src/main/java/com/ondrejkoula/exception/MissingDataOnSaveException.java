package com.ondrejkoula.exception;

import java.util.Map;

public class MissingDataOnSaveException extends RuntimeException {

    private Map<String, String> errorMessages;

    public MissingDataOnSaveException(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
