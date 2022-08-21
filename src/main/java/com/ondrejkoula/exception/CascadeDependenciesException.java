package com.ondrejkoula.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CascadeDependenciesException extends RuntimeException {

    private final Long parentEntityId;
    
    private final String operation;

    private final Map<String, Integer> typesAndOccurrences;

    public CascadeDependenciesException(Long parentEntityId, String operation, Map<String, Integer> typesAndOccurrences) {
        this.parentEntityId = parentEntityId;
        this.operation = operation;
        this.typesAndOccurrences = typesAndOccurrences;
    }
}
