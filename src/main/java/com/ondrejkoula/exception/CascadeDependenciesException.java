package com.ondrejkoula.exception;

import lombok.Getter;

import java.util.Map;

@Getter

public class CascadeDependenciesException extends RuntimeException {

    private Long parentEntityId;

    private Map<String, Integer> typesAndOccurrences;

    public CascadeDependenciesException(Long parentEntityId, Map<String, Integer> typesAndOccurrences) {
        this.parentEntityId = parentEntityId;
        this.typesAndOccurrences = typesAndOccurrences;
    }
}
