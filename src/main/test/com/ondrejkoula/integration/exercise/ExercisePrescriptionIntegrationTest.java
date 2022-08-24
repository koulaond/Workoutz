package com.ondrejkoula.integration.exercise;

import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.integration.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ExercisePrescriptionIntegrationTest extends IntegrationTest {

    static final String URL_PREFIX = "http://localhost:8080/api/v1/exercises/prescription";
    RestTemplate restTemplate = new RestTemplate();

    @Test
    void whenAllRequiredFieldsAreSet_thenCreateExercisePrescription() {
        ExerciseTypeDTO exerciseTypeDTO = ExerciseTypeDTO.builder().category("typeCategory").value("exerciseType").build();
        ResponseEntity<ExerciseTypeDTO> exerciseTypeResponse = restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/types", exerciseTypeDTO, ExerciseTypeDTO.class);

        ExerciseTypeDTO createdExerciseType = exerciseTypeResponse.getBody();
        Assertions.assertNotNull(createdExerciseType);
        
        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder()
                .exerciseType(ExerciseTypeDTO.builder().id(1L).category("testType").build())
                .label("label")
                .build();

        ResponseEntity<ExercisePrescriptionDTO> prescriptionResponse = restTemplate.postForEntity(URL_PREFIX, toCreate, ExercisePrescriptionDTO.class);

        ExercisePrescriptionDTO createdExercisePrescription = prescriptionResponse.getBody();
        Assertions.assertNotNull(createdExercisePrescription);
        
        Assertions.assertEquals(createdExercisePrescription.getExerciseType().getId(), createdExerciseType.getId());
    }
    
    @Test
    void whenLabelAndExerciseTypeReferenceIdIsMissing_thenReturnErrorMessage() {
        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder()
                .exerciseType(ExerciseTypeDTO.builder().category("testType").build())
                .build();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(URL_PREFIX, toCreate, Object.class));
        assertEquals("400 : \"{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"exerciseType\":\"REFERENCE_ID_IS_MISSING\",\"label\":\"MISSING_FIELD_CONTENT\"}}\"", exception.getMessage());
    }

    @Test
    void whenReferencedObjectNotExist_thenReturnErrorMessage() {
        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder()
                .exerciseType(ExerciseTypeDTO.builder().id(1L).category("testType").build())
                .label("label")
                .build();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(URL_PREFIX, toCreate, Object.class));
        assertEquals("400 : \"{\"errorMessage\":\"Unexpected data integrity internal error\",\"messageCode\":\"GENERAL_ERROR\"}\"", exception.getMessage());
    }
}
