package com.ondrejkoula.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class MissingDataOnSaveException extends RuntimeException {

    private final Map<String, String> errorMessages;

    public MissingDataOnSaveException(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
