package com.ondrejkoula.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondrejkoula.domain.CompositionChild;
import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.AbstractDTO;
import com.ondrejkoula.dto.datachange.DataChangeOperation;
import com.ondrejkoula.dto.datachange.composition.*;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.exception.IncorrectParentException;
import com.ondrejkoula.exception.UnsupportedCompositeChangeTypeException;
import com.ondrejkoula.service.CompositionService;
import com.ondrejkoula.service.GenericService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
public abstract class CompositionEndpoint<P extends DomainEntity, PDTO extends AbstractDTO, CH extends CompositionChild<P>, CDTO extends AbstractDTO>
        extends CrudEndpoint<P, PDTO, CompositionService<CH, P>> {

    private final GenericService<CH> childService;

    public CompositionEndpoint(GenericService<CH> childService, CompositionService<CH, P> parentService) {
        super(parentService);
        this.childService = childService;
    }

    @PutMapping(value = "/update-children/{id}", produces = "application/json")
    public PDTO updateChildren(@PathVariable("id") Long parentId, @RequestBody CompositionChanges dataChanges) {
        verifyParentExists(parentId);

        dataChanges.getChanges().forEach(compositionChange -> {
            DataChangeOperation operation = compositionChange.getOperation();
            JsonNode value = compositionChange.getValue();
            processChange(operation, value, parentId);
        });

        return toDTO(service.findById(parentId));
    }

    private void verifyParentExists(Long parentId) {
        try {
            service.findById(parentId);

        } catch (DataNotFoundException e) {
            log.warn("Parent with ID {} not found.", parentId);
            throw e;
        }
    }

    @SneakyThrows
    private void processChange(DataChangeOperation operation, JsonNode value, Long parentId) {
        ObjectMapper objectMapper = new ObjectMapper();

        switch (operation) {
            case DELETE:
                processDeleteChild(value, objectMapper, parentId);
                break;
            case ADD:
                processAssignNewChild(value, objectMapper, parentId);
                break;
            case UPDATE:
                processUpdateChild(value, objectMapper, parentId);
                break;
            case CHANGE_POSITION:
                processChangeChildPosition(value, objectMapper, parentId);
                break;
            default:
                throw new UnsupportedCompositeChangeTypeException(operation.name(), parentId);
        }
    }

    @SneakyThrows
    protected AddNewChildCompositionChange<CDTO> getValueForCreate(ObjectMapper objectMapper, JsonNode value) {
        Class<CDTO> dtoClass = getDtoClass();
        JavaType type = objectMapper.getTypeFactory().constructParametricType(AddNewChildCompositionChange.class, dtoClass);
        return objectMapper.readValue(value.toString(), type);
    }

    protected abstract Class<CDTO> getDtoClass();

    protected abstract CH convertChildDtoToDomain(CDTO childDto);

    private void processChangeChildPosition(JsonNode value, ObjectMapper objectMapper, Long parentId) throws JsonProcessingException {
        UpdatePositionCompositionChange updatePositionCompositionChange = objectMapper.treeToValue(value, UpdatePositionCompositionChange.class);
        validateChildBelongsToParent(updatePositionCompositionChange.getChildId(), parentId);
        service.changeChildPosition(updatePositionCompositionChange.getChildId(), updatePositionCompositionChange.getNewPosition());
    }


    private void processUpdateChild(JsonNode value, ObjectMapper objectMapper, Long parentId) throws JsonProcessingException {
        UpdateChildCompositionChange updateChildCompositionChange = objectMapper.treeToValue(value, UpdateChildCompositionChange.class);
        validateChildBelongsToParent(updateChildCompositionChange.getChildId(), parentId);
        service.update(updateChildCompositionChange.getChildId(), updateChildCompositionChange.getData());
    }

    private void processAssignNewChild(JsonNode value, ObjectMapper objectMapper, Long parentId) {
        AddNewChildCompositionChange<CDTO> valueForCreate = getValueForCreate(objectMapper, value);
        CDTO dataDto = valueForCreate.getData();
        CH data = convertChildDtoToDomain(dataDto);
        service.assignNewChildToParent(parentId, data);
    }

    private void processDeleteChild(JsonNode value, ObjectMapper objectMapper, Long parentId) throws JsonProcessingException {
        DeleteChildCompositionChange deleteChildCompositionChange = objectMapper.treeToValue(value, DeleteChildCompositionChange.class);
        validateChildBelongsToParent(deleteChildCompositionChange.getId(), parentId);
        service.removeExistingChildFromParent(deleteChildCompositionChange.getId());
    }

    private void validateChildBelongsToParent(Long childId, Long parentId) {
        CH child = childService.findById(childId);

        if (!parentId.equals(child.getParent().getId())) {
            throw new IncorrectParentException(childId, parentId);
        }
    }
}
