package com.ondrejkoula.dto.datachange;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DataChange {

    private Object value;

    private String operation;

}
