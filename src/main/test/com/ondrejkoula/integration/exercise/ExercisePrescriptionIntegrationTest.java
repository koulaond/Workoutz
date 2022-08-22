package com.ondrejkoula.integration.exercise;

import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class ExercisePrescriptionIntegrationTest extends IntegrationTest {

    static final String URL_PREFIX = "http://localhost:8080/api/v1/exercises/prescription";
    RestTemplate restTemplate = new RestTemplate();

    @Test
    void whenSomeDataIsMissingOnUpdate_thenReturnError() {
        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder()
                .exerciseType(ExerciseTypeDTO.builder().category("testType").build())
                .label("prescriptionLabel").build();

        assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(URL_PREFIX, toCreate, Object.class),
                "{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"exerciseType\":\"VALIDATION_REFERENCE_ID_IS_MISSING\"}}");
    }
}
