package com.ondrejkoula.integration.exercise;

import com.ondrejkoula.dto.datachange.DataChange;
import com.ondrejkoula.dto.datachange.DataChanges;
import com.ondrejkoula.dto.exercise.ConditionDTO;
import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.dto.exercise.HighIntensityIntervalDTO;
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


public class ExercisePrescriptionIntegrationTest extends IntegrationTest {

    static final String URL_PREFIX = "http://localhost:8080/api/v1/exercises/prescription";
    RestTemplate restTemplate = new RestTemplate();

    @Test
    void create_whenAllRequiredFieldsAreSet_thenCreateExercisePrescription() {
        ExerciseTypeDTO exerciseTypeDTO = ExerciseTypeDTO.builder().category("typeCategory").value("exerciseType").build();
        ResponseEntity<ExerciseTypeDTO> exerciseTypeResponse = restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/types", exerciseTypeDTO, ExerciseTypeDTO.class);

        ExerciseTypeDTO createdExerciseType = exerciseTypeResponse.getBody();
        Assertions.assertNotNull(createdExerciseType);

        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder()
                .exerciseType(createdExerciseType)
                .label("label")
                .build();

        ResponseEntity<ExercisePrescriptionDTO> prescriptionResponse = restTemplate.postForEntity(URL_PREFIX, toCreate, ExercisePrescriptionDTO.class);

        ExercisePrescriptionDTO createdExercisePrescription = prescriptionResponse.getBody();
        Assertions.assertNotNull(createdExercisePrescription);

        assertEquals(createdExercisePrescription.getExerciseType().getId(), createdExerciseType.getId());
    }

    @Test
    void create_whenLabelAndExerciseTypeReferenceIdIsMissing_thenReturnErrorMessage() {
        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder()
                .exerciseType(ExerciseTypeDTO.builder().category("testType").build())
                .build();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(URL_PREFIX, toCreate, Object.class));
        assertEquals("400 : \"{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"exerciseType\":\"REFERENCE_ID_IS_MISSING\",\"label\":\"MISSING_FIELD_CONTENT\"}}\"", exception.getMessage());
    }

    @Test
    void create_whenReferencedObjectNotExist_thenReturnErrorMessage() {
        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder()
                .exerciseType(ExerciseTypeDTO.builder().id(1L).build())
                .label("label")
                .build();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(URL_PREFIX, toCreate, Object.class));
        assertEquals("400 : \"{\"errorMessage\":\"Unexpected data integrity internal error\",\"messageCode\":\"GENERAL_ERROR\"}\"", exception.getMessage());
    }
    
    @Test
    void update_whenAllRequiredFieldsAreSet_thenUpdateExercisePrescription() {
        ExerciseTypeDTO exerciseTypeDTO = ExerciseTypeDTO.builder().category("typeCategory").value("exerciseType").build();
        ResponseEntity<ExerciseTypeDTO> exerciseTypeResponse = restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/types", exerciseTypeDTO, ExerciseTypeDTO.class);

        ExerciseTypeDTO createdExerciseType = exerciseTypeResponse.getBody();
        Assertions.assertNotNull(createdExerciseType);

        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder()
                .exerciseType(createdExerciseType)
                .label("label")
                .build();

        ResponseEntity<ExercisePrescriptionDTO> prescriptionResponse = restTemplate.postForEntity(URL_PREFIX, toCreate, ExercisePrescriptionDTO.class);

        ExercisePrescriptionDTO createdExercisePrescription = prescriptionResponse.getBody();
        Assertions.assertNotNull(createdExercisePrescription);

        assertEquals(createdExercisePrescription.getExerciseType().getId(), createdExerciseType.getId());

        DataChanges dataChanges = DataChanges.builder()
                .changes(Collections.singletonMap("label", DataChange.builder().operation("update").value("updatedLabel").build()))
                .build();

       restTemplate.put(URL_PREFIX + "/" + createdExercisePrescription.getId(), dataChanges, DataChanges.class);
    }

    @Test
    void update_whenAllRequiredValuesMissing_thenReturnErrorMessage() {
        ExerciseTypeDTO exerciseTypeDTO = ExerciseTypeDTO.builder().category("typeCategory").value("exerciseType").build();
        ResponseEntity<ExerciseTypeDTO> exerciseTypeResponse = restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/types", exerciseTypeDTO, ExerciseTypeDTO.class);

        ExerciseTypeDTO createdExerciseType = exerciseTypeResponse.getBody();
        Assertions.assertNotNull(createdExerciseType);

        ExercisePrescriptionDTO toCreate = ExercisePrescriptionDTO.builder()
                .exerciseType(createdExerciseType)
                .label("label")
                .build();

        ResponseEntity<ExercisePrescriptionDTO> prescriptionResponse = restTemplate.postForEntity(URL_PREFIX, toCreate, ExercisePrescriptionDTO.class);

        ExercisePrescriptionDTO createdExercisePrescription = prescriptionResponse.getBody();
        Assertions.assertNotNull(createdExercisePrescription);

        assertEquals(createdExercisePrescription.getExerciseType().getId(), createdExerciseType.getId());

        DataChanges dataChanges = DataChanges.builder()
                .changes(getChangesForDeleteAll())
                .build();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.put(URL_PREFIX + "/" + createdExercisePrescription.getId(), dataChanges, DataChanges.class));
        assertEquals("400 : \"{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"label\":\"MISSING_FIELD_CONTENT\"}}\"", exception.getMessage());

    }

    @NotNull
    private Map<String, DataChange> getChangesForDeleteAll() {
        return Map.of(
                "exerciseType", DataChange.builder().operation("delete").build(),
                "label", DataChange.builder().operation("delete").build()
        );
    }

    @Test
    void delete_successfulCase() {
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

        restTemplate.delete(URL_PREFIX + "/" + createdExercisePrescription.getId(), createdExercisePrescription.getId());
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.getForEntity(URL_PREFIX + "/" + createdExercisePrescription.getId(), ExercisePrescriptionDTO.class));
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

        ResponseEntity<ExercisePrescriptionDTO> prescriptionResponse = restTemplate.postForEntity(URL_PREFIX, toCreate, ExercisePrescriptionDTO.class);

        ExercisePrescriptionDTO createdExercisePrescription = prescriptionResponse.getBody();
        Assertions.assertNotNull(createdExercisePrescription);

        ConditionDTO condition = ConditionDTO.builder().exercisePrescription(createdExercisePrescription).timeSec(30).timeMin(30).build();
        restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/condition", condition, ConditionDTO.class);

        HighIntensityIntervalDTO hiit = HighIntensityIntervalDTO.builder().exercisePrescription(createdExercisePrescription)
                .intensityIntervalTime(1).intervalsCount(1).calmIntervalTime(1).build();
        restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/hiit", hiit, HighIntensityIntervalDTO.class);

        HttpClientErrorException httpClientErrorException = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.delete(URL_PREFIX + "/" + createdExercisePrescription.getId(), createdExercisePrescription.getId()));
        assertEquals("400 : \"{\"errorMessage\":\"Unable to finish an operation due to existing dependencies.\",\"messageCode\":\"CASCADE_DEPENDENCIES\",\"parentEntityId\":2,\"operation\":null,\"dependencies\":[{\"type\":\"HIGH_INTENSITY_INTERVAL\",\"ids\":[4]},{\"type\":\"CONDITION\",\"ids\":[3]}]}\"", httpClientErrorException.getMessage());
    }

}
