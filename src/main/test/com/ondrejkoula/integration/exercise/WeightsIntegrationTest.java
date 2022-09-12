package com.ondrejkoula.integration.exercise;

import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.dto.exercise.weights.WeightsDTO;
import com.ondrejkoula.integration.IntegrationTest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class WeightsIntegrationTest extends IntegrationTest {

    static final String URL_PREFIX = "http://localhost:8080/api/v1/exercises/weights";

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void create_whenAllRequiredFieldsAreSet_thenCreateWeights() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);

        WeightsDTO weightsToCreate = WeightsDTO.builder().exercisePrescription(createdExercisePrescription).maxTimeMin(30).maxTimeSec(30).build();

        restTemplate.postForEntity(URL_PREFIX, weightsToCreate, WeightsDTO.class);

    }

    @Test
    void create_whenAllRequiredFieldsMissing_thenReturnErrorMessage() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);

        WeightsDTO weightsToCreate = WeightsDTO.builder().build();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(URL_PREFIX, weightsToCreate, WeightsDTO.class));
        assertEquals("400 : \"{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"maxTimeMin\":\"MISSING_FIELD_CONTENT\",\"maxTimeSec\":\"MISSING_FIELD_CONTENT\",\"exercisePrescription\":\"MISSING_REFERENCE\"}}\"", exception.getMessage());


    }

    @NotNull
    private ExercisePrescriptionDTO createExercisePrescription(ExerciseTypeDTO createdExerciseType) {
        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder().exerciseType(createdExerciseType).label("label").build();
        ResponseEntity<ExercisePrescriptionDTO> response = restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/prescription", toCreate, ExercisePrescriptionDTO.class);
        assertNotNull(response.getBody());
        return response.getBody();
    }

    @NotNull
    private ExerciseTypeDTO createExerciseType() {
        ExerciseTypeDTO exerciseTypeDTO = ExerciseTypeDTO.builder().category("typeCategory").value("exerciseType").build();
        ResponseEntity<ExerciseTypeDTO> response = restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/types", exerciseTypeDTO, ExerciseTypeDTO.class);
        assertNotNull(response.getBody());
        return response.getBody();
    }
}
