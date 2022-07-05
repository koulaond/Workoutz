package com.ondrejkoula.exception;

import lombok.Getter;

@Getter
public class IncorrectParentException extends RuntimeException {

    private final Long childId;

    private final Long invalidParentId;

    public IncorrectParentException(Long childId, Long invalidParentId) {
        this.childId = childId;
        this.invalidParentId = invalidParentId;
    }
}
