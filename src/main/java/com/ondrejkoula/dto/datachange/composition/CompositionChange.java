package com.ondrejkoula.dto.datachange.composition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.dto.datachange.DataChangeOperation;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompositionChange {

    private DataChangeOperation operation;

    private Object value;
}
