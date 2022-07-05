package com.ondrejkoula.exception;

public class ParentNotFoundException extends RuntimeException {

    public ParentNotFoundException(Throwable cause) {
        super(cause);
    }
}
