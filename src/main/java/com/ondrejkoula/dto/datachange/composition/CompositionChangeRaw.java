package com.ondrejkoula.dto.datachange.composition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.ondrejkoula.dto.datachange.DataChangeOperation;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompositionChangeRaw {

    private DataChangeOperation operation;

    private JsonNode value;

    @Builder
    public CompositionChangeRaw(DataChangeOperation operation, JsonNode value) {
        this.operation = operation;
        this.value = value;
    }
}

