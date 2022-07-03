package com.ondrejkoula.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.dto.datachange.DataChange;
import com.ondrejkoula.dto.datachange.DataChangeOperation;
import com.ondrejkoula.dto.datachange.DataChanges;
import com.ondrejkoula.dto.datachange.composition.CompositionChangeRaw;
import com.ondrejkoula.dto.datachange.composition.CompositionChanges;
import com.ondrejkoula.dto.exercise.superset.SuperSetDTO;
import com.ondrejkoula.endpoint.exercise.SuperSetEndpoint;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.service.exercise.superset.SuperSetExerciseService;
import com.ondrejkoula.service.exercise.superset.SuperSetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    private JsonNode dataOfChildUpdateChanges;
    private JsonNode dataOfChangePositionDto;
    private JsonNode dataOfDeleteChildDto;

    @BeforeEach
    void setup() throws JsonProcessingException {
        superSetEndpoint = new SuperSetEndpoint(superSetExerciseService, superSetService);

        ObjectMapper objectMapper = new ObjectMapper();

        dataOfFirstChildToAdd = objectMapper.readTree("{ \"data\": {\"position\": 0, \"repetitionsCount\": 42}}");
        dataOfSecondChildToAdd = objectMapper.readTree("{ \"data\": {\"position\": 0, \"repetitionsCount\": 43}}");
        dataOfChildUpdateChanges = objectMapper.readTree("{\"childId\": 1, \"data\":  {\"changes\":  {\"field\":  {\"value\":  1, \"operation\": \"update\"}}}}");
        dataOfChangePositionDto = objectMapper.readTree("{\"childId\": 1, \"newPosition\":  2}");
        dataOfDeleteChildDto = objectMapper.readTree("{\"id\": 1}");

    }

    @Test
    void childrenShouldBeUpdatedSuccessfully() {
        doReturn(prepareSuperSetExercise()).when(superSetExerciseService).findById(1L);
        doReturn(SuperSet.builder().id(10L).build()).when(superSetService).findById(10L);

        CompositionChanges changes = prepareChangesDtoForEndpoint();
        SuperSetDTO superSetDTO = superSetEndpoint.updateChildren(10L, changes);

        verifyAllNecessaryServicesCalled();
        assertThat(superSetDTO.getId()).isEqualTo(10L);
    }

    @Test
    void whenParentDoesNotExist_thenExceptionIsThrown() {
        Mockito.doThrow(DataNotFoundException.class).when(superSetService).findById(10L);

        CompositionChanges changes = prepareChangesDtoForEndpoint();
        assertThrows(DataNotFoundException.class, () -> superSetEndpoint.updateChildren(10L, changes));
    }

    private void verifyAllNecessaryServicesCalled() {
        SuperSetExercise expectedFirstSavedObjectToBeReturned = prepareSuperSetExerciseWithRepetitionsCount(42);
        SuperSetExercise expectedSecondSavedObjectToBeReturned = prepareSuperSetExerciseWithRepetitionsCount(43);

        DataChanges dataChanges = DataChanges.builder()
                .changes(Collections.singletonMap("field", DataChange.builder().value(1).operation("update").build()))
                .build();

        verify(superSetService).assignNewChildToParent(10L, expectedFirstSavedObjectToBeReturned);
        verify(superSetService).assignNewChildToParent(10L, expectedSecondSavedObjectToBeReturned);
        verify(superSetService).update(1L, dataChanges);
        verify(superSetService).changeChildPosition(1L,2);
        verify(superSetService).removeExistingChildFromParent(1L);
    }

    private CompositionChanges prepareChangesDtoForEndpoint() {
        return CompositionChanges.builder()
                .changes(Arrays.asList(
                        CompositionChangeRaw.builder().operation(DataChangeOperation.ADD).value(dataOfFirstChildToAdd).build(),
                        CompositionChangeRaw.builder().operation(DataChangeOperation.ADD).value(dataOfSecondChildToAdd).build(),
                        CompositionChangeRaw.builder().operation(DataChangeOperation.UPDATE).value(dataOfChildUpdateChanges).build(),
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
