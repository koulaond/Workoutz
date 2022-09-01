package com.ondrejkoula.exception;

import com.ondrejkoula.dto.Dependencies;
import lombok.Getter;

import java.util.List;

@Getter
public class CascadeDependenciesException extends RuntimeException {

    private final Long parentEntityId;
    
    private final String operation;

   private final List<Dependencies> dependencies;

    public CascadeDependenciesException(Long parentEntityId, String operation, List<Dependencies> dependencies) {
        this.parentEntityId = parentEntityId;
        this.operation = operation;
        this.dependencies = dependencies;
    }
}
