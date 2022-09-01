package com.ondrejkoula.dto.error;

import com.ondrejkoula.dto.Dependencies;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CascadeDependenciesErrorDetailDto extends ErrorDetailDto {

    private final Long parentEntityId;

    private final String operation;
    
    private final List<Dependencies> dependencies;

    @Builder
    public CascadeDependenciesErrorDetailDto(String errorMessage, String messageCode,
                                             Long parentEntityId, String operation, List<Dependencies> dependencies) {
        super(errorMessage, messageCode);
        this.parentEntityId = parentEntityId;
        this.operation = operation;
        this.dependencies = dependencies;
    }
}
