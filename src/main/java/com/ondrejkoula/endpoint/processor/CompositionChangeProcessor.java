package com.ondrejkoula.endpoint.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondrejkoula.dto.datachange.DataChangeOperation;
import com.ondrejkoula.dto.datachange.DataChanges;
import com.ondrejkoula.dto.datachange.composition.CompositionChangeValue;
import com.ondrejkoula.dto.datachange.composition.DeleteChildCompositionChange;
import com.ondrejkoula.dto.datachange.composition.UpdatePositionCompositionChange;
import com.ondrejkoula.exception.UnsupportedCompositeChangeTypeException;

import java.io.IOException;

public abstract class CompositionChangeProcessor<T extends CompositionChangeValue> {

    public CompositionChangeValue deserialize(DataChangeOperation operation, JsonNode value) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        switch (operation) {
            case DELETE:
                return objectMapper.treeToValue(value, DeleteChildCompositionChange.class);
            case ADD:
                return getValueForCreate(objectMapper, value);
            case UPDATE:
                return objectMapper.treeToValue(value, DataChanges.class);
            case CHANGE_POSITION:
                return objectMapper.treeToValue(value, UpdatePositionCompositionChange.class);
            default:
                throw new UnsupportedCompositeChangeTypeException(operation.name());
        }
    }

    public abstract T getValueForCreate(ObjectMapper objectMapper, JsonNode value);

}
