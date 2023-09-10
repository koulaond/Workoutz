package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.endpoint.exercise.ExerciseTypeEndpoint;
import com.ondrejkoula.service.exercise.ExerciseTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.ondrejkoula.domain.exercise.ExerciseType.builder;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CreateReadDeleteEndpointTest {

    @Mock
    ExerciseTypeService exerciseTypeService;

    ExerciseTypeEndpoint endpoint;

    @BeforeEach
    void setup() {
        endpoint = new ExerciseTypeEndpoint(exerciseTypeService);
    }

    @Test
    void testGet() {
        Mockito.doReturn(buildTestExerciseType())
                .when(exerciseTypeService).findById(1L);

        ExerciseTypeDTO exerciseTypeDTO = endpoint.get(1L);

        assertThat(exerciseTypeDTO.getNote()).isEqualTo("exerciseType");
    }

    @Test
    void testList() {
        Mockito.doReturn(singletonList(buildTestExerciseType()))
                .when(exerciseTypeService).findAll();

        List<ExerciseTypeDTO> list = endpoint.list();

        assertThat(list).hasSize(1)
                .satisfies(exerciseTypeDTO -> assertThat(exerciseTypeDTO.get(0).getNote()).isEqualTo("exerciseType"));
    }


    @Test
    void testCreate() {
        ExerciseType expected = buildTestExerciseType();
        Mockito.doReturn(expected).when(exerciseTypeService).create(ArgumentMatchers.any());

        ExerciseTypeDTO returned = endpoint.create(ExerciseTypeDTO.builder().note("exerciseType").build());

        assertThat(returned.getNote()).isEqualTo(expected.getNote());
    }
    private ExerciseType buildTestExerciseType() {
        return builder().note("exerciseType").build();
    }

}
