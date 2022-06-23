package com.ondrejkoula.exception;

import lombok.Value;

@Value
public class UnsupportedCompositeChangeTypeException extends RuntimeException {

    String unsupportedChangeType;

    public UnsupportedCompositeChangeTypeException(String unsupportedChangeType) {
        this.unsupportedChangeType = unsupportedChangeType;
    }
}
