package com.ondrejkoula.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondrejkoula.domain.CompositionChild;
import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.AbstractDTO;
import com.ondrejkoula.dto.datachange.DataChangeOperation;
import com.ondrejkoula.dto.datachange.composition.*;
import com.ondrejkoula.exception.UnsupportedCompositeChangeTypeException;
import com.ondrejkoula.service.CompositionChildService;
import com.ondrejkoula.service.GenericService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class CompositionEndpoint<P extends DomainEntity, PDTO extends AbstractDTO, CH extends CompositionChild<P>, CDTO extends AbstractDTO>
        extends CrdEndpoint<P, PDTO, CompositionChildService<CH, P>> {

    private final GenericService<P> parentService;

    public CompositionEndpoint(GenericService<P> parentService, CompositionChildService<CH, P> childService) {
        super(childService);
        this.parentService = parentService;
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public PDTO update(@PathVariable("id") Long id, @RequestBody CompositionChanges dataChanges) {
        dataChanges.getChanges().forEach(compositionChange -> {
            DataChangeOperation operation = compositionChange.getOperation();
            JsonNode value = compositionChange.getValue();
            processChange(operation, value);
        });
        return null;
    }

    @SneakyThrows // TODO throw custom exception
    public void processChange(DataChangeOperation operation, JsonNode value)  {
        ObjectMapper objectMapper = new ObjectMapper();

        switch (operation) {
            case DELETE:
                processDeleteChild(value, objectMapper);
            case ADD:
                processAssignNewChild(value, objectMapper);
            case UPDATE:
                processUpdateChild(value, objectMapper);
            case CHANGE_POSITION:
                processChangeChildPosition(value, objectMapper);
            default:
                throw new UnsupportedCompositeChangeTypeException(operation.name());
        }
    }
    
    protected abstract AddNewChildCompositionChange<CDTO> getValueForCreate(ObjectMapper objectMapper, JsonNode value);

    protected abstract CH convertChildDtoToDomain(CDTO childDto);

    private void processChangeChildPosition(JsonNode value, ObjectMapper objectMapper) throws JsonProcessingException {
        UpdatePositionCompositionChange updatePositionCompositionChange = objectMapper.treeToValue(value, UpdatePositionCompositionChange.class);
        service.changeItemPosition(updatePositionCompositionChange.getChildId(), updatePositionCompositionChange.getNewPosition());
    }

    private void processUpdateChild(JsonNode value, ObjectMapper objectMapper) throws JsonProcessingException {
        UpdateChildCompositionChange updateChildCompositionChange = objectMapper.treeToValue(value, UpdateChildCompositionChange.class);
        service.update(updateChildCompositionChange.getChildId(), updateChildCompositionChange.getChildChanges());
    }

    private void processAssignNewChild(JsonNode value, ObjectMapper objectMapper) {
        AddNewChildCompositionChange<CDTO> valueForCreate = getValueForCreate(objectMapper, value);
        CDTO dataDto = valueForCreate.getData();
        CH data = convertChildDtoToDomain(dataDto);
        service.assignNewItemToParent(valueForCreate.getPosition(), data);
    }

    private void processDeleteChild(JsonNode value, ObjectMapper objectMapper) throws JsonProcessingException {
        DeleteChildCompositionChange deleteChildCompositionChange = objectMapper.treeToValue(value, DeleteChildCompositionChange.class);
        service.deleteById(deleteChildCompositionChange.getId());
    }


}
