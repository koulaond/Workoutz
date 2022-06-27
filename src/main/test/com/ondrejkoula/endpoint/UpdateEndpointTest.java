package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.dto.datachange.DataChanges;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.endpoint.exercise.ExerciseTypeEndpoint;
import com.ondrejkoula.service.exercise.ExerciseTypeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ondrejkoula.domain.exercise.ExerciseType.builder;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UpdateEndpointTest {

    @Mock
    ExerciseTypeService exerciseTypeService;

    ExerciseTypeEndpoint endpoint;

    @BeforeEach
    void setup() {
        endpoint = new ExerciseTypeEndpoint(exerciseTypeService);
    }

    @Test
    void update() {
        ExerciseType expected = buildTestExerciseType();
        DataChanges dataChanges = new DataChanges();

        Mockito.doReturn(expected).when(exerciseTypeService).update(1L, dataChanges);

        ExerciseTypeDTO updated = endpoint.update(1L, dataChanges);

        Assertions.assertThat(updated.getNote()).isEqualTo("exerciseType");
    }

    private ExerciseType buildTestExerciseType() {
        return builder().note("exerciseType").build();
    }

}