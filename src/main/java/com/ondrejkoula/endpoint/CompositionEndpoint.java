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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class CompositionEndpoint<P extends DomainEntity, PDTO extends AbstractDTO, CH extends CompositionChild<P>, CDTO extends AbstractDTO>
        extends UpdateEndpoint<P, PDTO, CompositionChildService<CH, P>> {

    private final GenericService<CH> childService;

    public CompositionEndpoint(GenericService<CH> childService, CompositionChildService<CH, P> parentService) {
        super(parentService);
        this.childService = childService;
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public PDTO updateChildren(@PathVariable("id") Long id, @RequestBody CompositionChanges dataChanges) {
        P parent = service.findById(id);

        dataChanges.getChanges().forEach(compositionChange -> {
            DataChangeOperation operation = compositionChange.getOperation();
            JsonNode value = compositionChange.getValue();
            processChange(operation, value, parent);
        });
        return null;
    }

    @SneakyThrows // TODO throw custom exception
    private void processChange(DataChangeOperation operation, JsonNode value, P parent)  {
        ObjectMapper objectMapper = new ObjectMapper();

        switch (operation) {
            case DELETE:
                processDeleteChild(value, objectMapper, parent);
            case ADD:
                processAssignNewChild(value, objectMapper, parent);
            case UPDATE:
                processUpdateChild(value, objectMapper, parent);
            case CHANGE_POSITION:
                processChangeChildPosition(value, objectMapper, parent);
            default:
                throw new UnsupportedCompositeChangeTypeException(operation.name());
        }
    }
    
    protected abstract AddNewChildCompositionChange<CDTO> getValueForCreate(ObjectMapper objectMapper, JsonNode value);

    protected abstract CH convertChildDtoToDomain(CDTO childDto);

    private void processChangeChildPosition(JsonNode value, ObjectMapper objectMapper, P parent) throws JsonProcessingException {
        UpdatePositionCompositionChange updatePositionCompositionChange = objectMapper.treeToValue(value, UpdatePositionCompositionChange.class);
        validateChildBelongsToParent(updatePositionCompositionChange.getChildId(), parent.getId());
        service.changeChildPosition(updatePositionCompositionChange.getChildId(), updatePositionCompositionChange.getNewPosition());
    }


    private void processUpdateChild(JsonNode value, ObjectMapper objectMapper, P parent) throws JsonProcessingException {
        UpdateChildCompositionChange updateChildCompositionChange = objectMapper.treeToValue(value, UpdateChildCompositionChange.class);
        validateChildBelongsToParent(updateChildCompositionChange.getChildId(), parent.getId());
        service.update(updateChildCompositionChange.getChildId(), updateChildCompositionChange.getChildChanges());
    }

    private void processAssignNewChild(JsonNode value, ObjectMapper objectMapper, P parent) {
        AddNewChildCompositionChange<CDTO> valueForCreate = getValueForCreate(objectMapper, value);
        CDTO dataDto = valueForCreate.getData();
        CH data = convertChildDtoToDomain(dataDto);
        service.assignNewChildToParent(valueForCreate.getPosition(), data);
    }

    private void processDeleteChild(JsonNode value, ObjectMapper objectMapper, P parent) throws JsonProcessingException {
        DeleteChildCompositionChange deleteChildCompositionChange = objectMapper.treeToValue(value, DeleteChildCompositionChange.class);
        validateChildBelongsToParent(deleteChildCompositionChange.getId(), parent.getId());
        service.removeExistingChildFromParent(deleteChildCompositionChange.getId());
    }

    private void validateChildBelongsToParent(Long childId, Long id) {
        CH child = childService.findById(childId);
        if (!id.equals(child.getParent().getId())) {
            // TODO throw validation exception
        }
    }
}
