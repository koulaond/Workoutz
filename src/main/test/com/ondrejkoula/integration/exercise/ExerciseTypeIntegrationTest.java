package com.ondrejkoula.integration.exercise;

import com.ondrejkoula.dto.datachange.DataChange;
import com.ondrejkoula.dto.datachange.DataChanges;
import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.integration.IntegrationTest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExerciseTypeIntegrationTest extends IntegrationTest {

    static final String URL_PREFIX = "http://localhost:8080/api/v1/exercises/types";

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void create_whenAllRequiredFieldsAreSet_thenCreateExerciseType() {
        ExerciseTypeDTO exerciseTypeDTO = ExerciseTypeDTO.builder().category("typeCategory").value("exerciseType").build();
        ResponseEntity<ExerciseTypeDTO> exerciseTypeResponse = restTemplate.postForEntity(URL_PREFIX, exerciseTypeDTO, ExerciseTypeDTO.class);

        ExerciseTypeDTO createdExerciseType = exerciseTypeResponse.getBody();
        Assertions.assertNotNull(createdExerciseType);
    }

    @Test
    void update_whenAllRequiredFieldsAreSet_thenUpdateExerciseType() {
        ExerciseTypeDTO exerciseTypeDTO = ExerciseTypeDTO.builder().category("typeCategory").value("exerciseType").build();
        ResponseEntity<ExerciseTypeDTO> exerciseTypeResponse = restTemplate.postForEntity(URL_PREFIX, exerciseTypeDTO, ExerciseTypeDTO.class);

        ExerciseTypeDTO createdExerciseType = exerciseTypeResponse.getBody();
        Assertions.assertNotNull(createdExerciseType);

        DataChanges dataChanges = DataChanges.builder()
                .changes(Collections.singletonMap("category", DataChange.builder().operation("update").value("typeCategory").build()))
                .build();

        restTemplate.put(URL_PREFIX + "/" + createdExerciseType.getId(), dataChanges, DataChanges.class);
    }


    @Test
    void update_whenAnyRequiredValueIsMissing_thenReturnErrorMessage() {
        ExerciseTypeDTO exerciseTypeDTO = ExerciseTypeDTO.builder().category("typeCategory").value("exerciseType").build();
        ResponseEntity<ExerciseTypeDTO> exerciseTypeResponse = restTemplate.postForEntity(URL_PREFIX, exerciseTypeDTO, ExerciseTypeDTO.class);

        ExerciseTypeDTO createdExerciseType = exerciseTypeResponse.getBody();
        Assertions.assertNotNull(createdExerciseType);

        DataChanges dataChanges = DataChanges.builder()
                .changes(Collections.singletonMap("category", DataChange.builder().operation("delete").build()))
                .build();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.put(URL_PREFIX + "/" + createdExerciseType.getId(), dataChanges, DataChanges.class));
        assertEquals("400 : \"{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"category\":\"MISSING_FIELD_CONTENT\"}}\"", exception.getMessage());
    }

    @Test
    void update_whenAllRequiredValuesMissing_thenReturnErrorMessage() {
        ExerciseTypeDTO exerciseTypeDTO = ExerciseTypeDTO.builder().category("typeCategory").value("exerciseType").build();
        ResponseEntity<ExerciseTypeDTO> exerciseTypeResponse = restTemplate.postForEntity(URL_PREFIX, exerciseTypeDTO, ExerciseTypeDTO.class);

        ExerciseTypeDTO createdExerciseType = exerciseTypeResponse.getBody();
        Assertions.assertNotNull(createdExerciseType);
        
        DataChanges dataChanges = DataChanges.builder()
                .changes( getChangesForDeleteAll())
                .build();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.put(URL_PREFIX + "/" + createdExerciseType.getId(), dataChanges, DataChanges.class));
        assertEquals("400 : \"{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"category\":\"MISSING_FIELD_CONTENT\",\"value\":\"MISSING_FIELD_CONTENT\"}}\"", exception.getMessage());
    }

    @Test
    void create_whenValueAndCategoryIsMissing_thenReturnErrorMessage() {
        ExerciseTypeDTO toCreate = ExerciseTypeDTO.builder().build();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(URL_PREFIX, toCreate, Object.class));
        assertEquals("400 : \"{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"category\":\"MISSING_FIELD_CONTENT\",\"value\":\"MISSING_FIELD_CONTENT\"}}\"", exception.getMessage());
    }

    @Test
    void delete_successfulCase() {
        ExerciseTypeDTO exerciseTypeDTO = ExerciseTypeDTO.builder().category("typeCategory").value("exerciseType").build();
        ResponseEntity<ExerciseTypeDTO> exerciseTypeResponse = restTemplate.postForEntity(URL_PREFIX, exerciseTypeDTO, ExerciseTypeDTO.class);

        ExerciseTypeDTO createdExerciseType = exerciseTypeResponse.getBody();
        Assertions.assertNotNull(createdExerciseType);


        restTemplate.delete(URL_PREFIX + "/" + createdExerciseType.getId(), createdExerciseType.getId());
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.getForEntity(URL_PREFIX + "/" + createdExerciseType.getId(), ExerciseTypeDTO.class));
        assertEquals("404 : \"{\"errorMessage\":\"Data not found\",\"messageCode\":\"NOT_FOUND\"}\"", exception.getMessage());
    }

    @Test
    void delete_cannotDeleteDueToExistingDependencies() {
        ExerciseTypeDTO exerciseTypeDTO = ExerciseTypeDTO.builder().category("typeCategory").value("exerciseType").build();
        ResponseEntity<ExerciseTypeDTO> exerciseTypeResponse = restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/types", exerciseTypeDTO, ExerciseTypeDTO.class);

        ExerciseTypeDTO createdExerciseType = exerciseTypeResponse.getBody();
        Assertions.assertNotNull(createdExerciseType);

        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder()
                .exerciseType(ExerciseTypeDTO.builder().id(1L).category("testType").build())
                .label("label")
                .build();

        ResponseEntity<ExercisePrescriptionDTO> createdPrescription = restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/prescription", toCreate, ExercisePrescriptionDTO.class);

        ExercisePrescriptionDTO createdExercisePrescription = createdPrescription.getBody();
        Assertions.assertNotNull(createdExercisePrescription);


        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.delete(URL_PREFIX + "/" + createdExerciseType.getId(), createdExerciseType.getId()));
        assertEquals("400 : \"{\"errorMessage\":\"Unable to finish an operation due to existing dependencies.\",\"messageCode\":\"CASCADE_DEPENDENCIES\",\"parentEntityId\":1,\"operation\":null,\"dependencies\":[{\"type\":\"EXERCISE_PRESCRIPTION\",\"ids\":[2]}]}\"", exception.getMessage());
    }

    @NotNull
    private Map<String, DataChange> getChangesForDeleteAll() {
        return Map.of(
                "category", DataChange.builder().operation("delete").build(),
                "value", DataChange.builder().operation("delete").build()
        );
    }
}
