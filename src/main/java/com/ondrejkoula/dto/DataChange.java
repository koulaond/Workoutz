package com.ondrejkoula.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DataChange {

    private Object value;

    private ChangeType changeType;

    public enum ChangeType {
        DELETE, UPDATE
    }
}
