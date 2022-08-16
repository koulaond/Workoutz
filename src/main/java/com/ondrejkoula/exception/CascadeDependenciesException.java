package com.ondrejkoula.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CascadeDependenciesException extends RuntimeException {

    private final Long parentEntityId;

    private final Map<String, Integer> typesAndOccurrences;

    public CascadeDependenciesException(Long parentEntityId, Map<String, Integer> typesAndOccurrences) {
        this.parentEntityId = parentEntityId;
        this.typesAndOccurrences = typesAndOccurrences;
    }
}
