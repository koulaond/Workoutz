package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class CascadeDependenciesErrorDetailDto extends ErrorDetailDto {

    private final Long parentEntityId;

    private final String operation;
    private final Map<String, Integer> dependenciesOccurrences;

    @Builder
    public CascadeDependenciesErrorDetailDto(String errorMessage, String messageCode,
                                             Long parentEntityId, String operation, Map<String, Integer> dependenciesOccurrences) {
        super(errorMessage, messageCode);
        this.parentEntityId = parentEntityId;
        this.operation = operation;
        this.dependenciesOccurrences = dependenciesOccurrences;
    }
}
