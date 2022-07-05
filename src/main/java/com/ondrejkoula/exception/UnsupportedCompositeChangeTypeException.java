package com.ondrejkoula.exception;

import lombok.Getter;

@Getter
public class UnsupportedCompositeChangeTypeException extends RuntimeException {

    private final String unsupportedChangeType;

    private final Long parentEntityId;

    public UnsupportedCompositeChangeTypeException(String unsupportedChangeType, Long parentEntityId) {
        this.unsupportedChangeType = unsupportedChangeType;
        this.parentEntityId = parentEntityId;
    }
}
