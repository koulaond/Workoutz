package com.ondrejkoula.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class MissingDataOnSaveException extends RuntimeException {

    private final Map<String, String> errorDetails;

    public MissingDataOnSaveException(Map<String, String> errorDetails) {
        this.errorDetails = errorDetails;
    }
}
