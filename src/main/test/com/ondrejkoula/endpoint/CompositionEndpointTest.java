package com.ondrejkoula.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.dto.datachange.DataChangeOperation;
import com.ondrejkoula.dto.datachange.composition.CompositionChangeRaw;
import com.ondrejkoula.dto.datachange.composition.CompositionChanges;
import com.ondrejkoula.dto.exercise.superset.SuperSetDTO;
import com.ondrejkoula.endpoint.exercise.SuperSetEndpoint;
import com.ondrejkoula.service.exercise.superset.SuperSetExerciseService;
import com.ondrejkoula.service.exercise.superset.SuperSetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CompositionEndpointTest {

    SuperSetEndpoint superSetEndpoint;

    @Mock
    SuperSetExerciseService superSetExerciseService;

    @Mock
    SuperSetService superSetService;
    private JsonNode dataOfFirstChildToAdd;
    private JsonNode dataOfSecondChildToAdd;
    private JsonNode dataOfChangePositionDto;
    private JsonNode dataOfDeleteChildDto;

    @BeforeEach
    void setup() throws JsonProcessingException {
        superSetEndpoint = new SuperSetEndpoint(superSetExerciseService, superSetService);

        ObjectMapper objectMapper = new ObjectMapper();

        dataOfFirstChildToAdd = objectMapper.readTree("{\"position\": 0, \"data\": {\"repetitionsCount\": 42}}");
        dataOfSecondChildToAdd = objectMapper.readTree("{\"position\": 0, \"data\": {\"repetitionsCount\": 43}}");
        dataOfChangePositionDto = objectMapper.readTree("{\"childId\": 1, \"newPosition\":  2}");
        dataOfDeleteChildDto = objectMapper.readTree("{\"id\": 1}");

        doReturn(prepareSuperSetExercise()).when(superSetExerciseService).findById(1L);

    }

    @Test
    void childrenShouldBeUpdatedSuccessfully() {
        CompositionChanges changes = prepareChangesDtoForEndpoint();

        SuperSetDTO superSetDTO = superSetEndpoint.updateChildren(10L, changes);

        verifyAllNecessaryServicesCalled();
    }

    private void verifyAllNecessaryServicesCalled() {
        SuperSetExercise expectedFirstSavedObjectToBeReturned = prepareSuperSetExerciseWithRepetitionsCount(42);
        SuperSetExercise expectedSecondSavedObjectToBeReturned = prepareSuperSetExerciseWithRepetitionsCount(43);

        verify(superSetService).assignNewChildToParent(10L, expectedFirstSavedObjectToBeReturned);
        verify(superSetService).assignNewChildToParent(10L, expectedSecondSavedObjectToBeReturned);
        verify(superSetService).changeChildPosition(1L,2);
        verify(superSetService).removeExistingChildFromParent(1L);
    }

    private CompositionChanges prepareChangesDtoForEndpoint() {
        return CompositionChanges.builder()
                .changes(Arrays.asList(
                        CompositionChangeRaw.builder().operation(DataChangeOperation.ADD).value(dataOfFirstChildToAdd).build(),
                        CompositionChangeRaw.builder().operation(DataChangeOperation.ADD).value(dataOfSecondChildToAdd).build(),
                        CompositionChangeRaw.builder().operation(DataChangeOperation.CHANGE_POSITION).value(dataOfChangePositionDto).build(),
                        CompositionChangeRaw.builder().operation(DataChangeOperation.DELETE).value(dataOfDeleteChildDto).build()
                ))
                .build();
    }

    private SuperSetExercise prepareSuperSetExerciseWithRepetitionsCount(Integer repetitionsCount) {
        return SuperSetExercise.builder().repetitionsCount(repetitionsCount).parent(SuperSet.builder().build()).build();
    }

    private SuperSetExercise prepareSuperSetExercise() {
        return SuperSetExercise.builder().parent(SuperSet.builder().id(10L).build()).build();
    }

}
