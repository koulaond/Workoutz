package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.dto.datachange.composition.CompositionChanges;
import com.ondrejkoula.dto.exercise.superset.SuperSetDTO;
import com.ondrejkoula.endpoint.CompositionEndpoint;
import com.ondrejkoula.service.exercise.superset.SuperSetExerciseService;
import com.ondrejkoula.service.exercise.superset.SuperSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/superset")
public class SuperSetEndpoint extends CompositionEndpoint<SuperSet, SuperSetDTO, SuperSetService> {

    private final SuperSetExerciseService exerciseService;

    @Autowired
    public SuperSetEndpoint(SuperSetService superSetService, SuperSetExerciseService exerciseService) {
        super(superSetService);
        this.exerciseService = exerciseService;
    }

    @Override
    public SuperSetDTO update(Long id, CompositionChanges dataChanges) {
      /*   dataChanges.getChanges().forEach(dataChange -> {
             DataChangeOperation operation = dataChange.getOperation();

             ObjectMapper mapper = new ObjectMapper();
             JsonNode valueAsJson = mapper.valueToTree(dataChange.getValue());

             switch (operation) {
                 case CHANGE_POSITION:
                     UpdatePositionCompositionChange changePositionDataChange = mapper.treeToValue(valueAsJson, UpdatePositionCompositionChange.class);
                     exerciseService.changeItemPosition(changePositionDataChange.getChildId(), changePositionDataChange.getNewPosition());
                     break;
                 case ADD:
                     SuperSetExercise superSetExercise = mapper.treeToValue(valueAsJson, SuperSetExercise.class);
                     exerciseService.assignNewItemToParent(id, superSetExercise);
                     break;
                 case UPDATE:
                     DataChanges itemDataChanges = mapper.treeToValue(valueAsJson, DataChanges.class);
                     exerciseService.update()
                     break;
                 case DELETE:
                     DeleteChildCompositionChange deleteChildChange = mapper.treeToValue(valueAsJson, DeleteChildCompositionChange.class);
             }
         });*/
        return null;
    }

    @Override
    protected SuperSet toDomain(SuperSetDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected SuperSetDTO toDTO(SuperSet domainEntity) {
        return domainEntity.toDTO();
    }

}
