package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.exercise.ExerciseType;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CrdEndpointTest {

    @Mock
    ExerciseTypeService exerciseTypeService;

    ExerciseTypeEndpoint endpoint;

    @BeforeEach
    void setup() {
        endpoint = new ExerciseTypeEndpoint(exerciseTypeService);
    }

    @Test
    void get() {
        Mockito.doReturn(ExerciseType.builder().note("exerciseType").build())
                .when(exerciseTypeService).findById(1L);

        ExerciseTypeDTO exerciseTypeDTO = endpoint.get(1L);
        Assertions.assertThat(exerciseTypeDTO.getNote()).isEqualTo("exerciseType");
    }

    @Test
    void list() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }
}