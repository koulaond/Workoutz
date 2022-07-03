package com.ondrejkoula.dto.datachange;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataChange {

    private Object value;

    private String operation;

    @Builder
    public DataChange(Object value, String operation) {
        this.value = value;
        this.operation = operation;
    }
}
