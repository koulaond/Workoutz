package com.ondrejkoula.integration.exercise;

import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class ExercisePrescriptionIntegrationTest extends IntegrationTest {

    static final String URL_PREFIX = "http://localhost:8080/api/v1/exercises/prescription";
    RestTemplate restTemplate = new RestTemplate();

    @Test
    void testCreateAndDelete() {
        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder()
                .exerciseType(ExerciseTypeDTO.builder().category("testType").build())
                .label("prescriptionLabel").build();

        ResponseEntity<ExercisePrescriptionDTO> created = restTemplate.postForEntity(URL_PREFIX, toCreate, ExercisePrescriptionDTO.class);

    }
}
