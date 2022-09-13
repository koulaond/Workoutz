package com.ondrejkoula.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnsupportedChangeTypeException extends RuntimeException {
    private String changeType;

    public UnsupportedChangeTypeException(String changeType) {
    
        this.changeType = changeType;
    }
}
