package com.ondrejkoula.exception;

import lombok.Getter;

@Getter
public class ParentNotFoundException extends RuntimeException {

  private final Long id;
  
  private final String type;

    public ParentNotFoundException(Long id, String type) {
        this.id = id;
        this.type = type;
    }
}
