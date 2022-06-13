package com.ondrejkoula.dto.datachange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompositeDataChange {

    private DataChangeOperation operation;

    private Object value;
}
