package com.ondrejkoula.integration.exercise;

import com.ondrejkoula.dto.datachange.DataChange;
import com.ondrejkoula.dto.datachange.DataChanges;
import com.ondrejkoula.dto.exercise.ConditionDTO;
import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.dto.workout.AssignExerciseToWorkoutDTO;
import com.ondrejkoula.dto.workout.WorkoutDTO;
import com.ondrejkoula.integration.IntegrationTest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.*;

public class ConditionIntegrationTest extends IntegrationTest {

    static final String URL_PREFIX = "http://localhost:8080/api/v1/exercises/condition";

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void create_whenAllRequiredFieldsAreSet_thenCreateCondition() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);

        ConditionDTO conditionToCreate = ConditionDTO.builder()
                .exercisePrescription(createdExercisePrescription).timeSec(1).timeMin(30).build();
      
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL_PREFIX, conditionToCreate, String.class);

        assertEquals("{\"id\":3,\"status\":null,\"note\":null,\"exercisePrescription\":{\"id\":2,\"status\":null,\"note\":null,\"label\":\"label\",\"exerciseType\":{\"id\":1,\"status\":null,\"note\":null,\"value\":\"exerciseType\",\"category\":\"typeCategory\"},\"description\":null},\"timeSec\":1,\"timeMin\":30}", responseEntity.getBody());
    }

    @Test
    void create_whenAllRequiredFieldsMissing_thenReturnErrorMessage() {
        ConditionDTO conditionToCreate = ConditionDTO.builder().build();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(URL_PREFIX, conditionToCreate, ConditionDTO.class));
        assertEquals("400 : \"{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"timeSec\":\"MISSING_FIELD_CONTENT\",\"timeMin\":\"MISSING_FIELD_CONTENT\",\"exercisePrescription\":\"MISSING_REFERENCE\"}}\"", exception.getMessage());
    }

    @Test
    void update_whenAllRequiredFieldsAreSetOrUndeleted_thenUpdateCondition() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);

        ConditionDTO conditionToCreate = ConditionDTO.builder().exercisePrescription(createdExercisePrescription).timeMin(30).timeSec(30).build();

        ResponseEntity<ConditionDTO> responseEntity = restTemplate.postForEntity(URL_PREFIX, conditionToCreate, ConditionDTO.class);
        ConditionDTO createdCondition = responseEntity.getBody();

        assertNotNull(createdCondition);

        DataChanges dataChanges = DataChanges.builder().changes(singletonMap("timeMin", DataChange.builder().operation("update").value(31).build())).build();
        restTemplate.put(URL_PREFIX + "/" + createdCondition.getId(), dataChanges, DataChanges.class);
    }

    @Test
    void update_whenSomeRequiredValueIsDeleted_thenReturnErrorMessage() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);

        ConditionDTO conditionToCreate = ConditionDTO.builder().exercisePrescription(createdExercisePrescription).timeMin(30).timeSec(30).build();

        ResponseEntity<ConditionDTO> responseEntity = restTemplate.postForEntity(URL_PREFIX, conditionToCreate, ConditionDTO.class);
        ConditionDTO createdCondition = responseEntity.getBody();

        assertNotNull(createdCondition);

        DataChanges dataChanges = DataChanges.builder().changes(getChanges()).build();
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.put(URL_PREFIX + "/" + createdCondition.getId(), dataChanges, DataChanges.class));
        assertEquals("400 : \"{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"timeSec\":\"MISSING_FIELD_CONTENT\",\"timeMin\":\"MISSING_FIELD_CONTENT\",\"exercisePrescription\":\"MISSING_REFERENCE\"}}\"", exception.getMessage());
    }

    @Test
    void delete_successfulCase() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);

        ConditionDTO conditionToCreate = ConditionDTO.builder().exercisePrescription(createdExercisePrescription).timeMin(30).timeSec(30).build();

        ResponseEntity<ConditionDTO> responseEntity = restTemplate.postForEntity(URL_PREFIX, conditionToCreate, ConditionDTO.class);
        ConditionDTO createdCondition = responseEntity.getBody();

        assertNotNull(createdCondition);

        restTemplate.delete(URL_PREFIX + "/" + createdCondition.getId());

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.getForEntity(URL_PREFIX + "/" + createdCondition.getId(), ConditionDTO.class));
        assertEquals("404 : \"{\"errorMessage\":\"Data not found\",\"messageCode\":\"NOT_FOUND\"}\"", exception.getMessage());
    }

    @Test
    void delete_cannotDeleteDueToExistingDependencies() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);

        ConditionDTO conditionToCreate = ConditionDTO.builder().exercisePrescription(createdExercisePrescription).timeSec(1).timeMin(2).build();

        ResponseEntity<ConditionDTO> responseEntity = restTemplate.postForEntity(URL_PREFIX, conditionToCreate, ConditionDTO.class);
        ConditionDTO createdCondition = responseEntity.getBody();

        assertNotNull(createdCondition);

        WorkoutDTO workoutDTO = WorkoutDTO.builder().label("workout").build();
        ResponseEntity<WorkoutDTO> workoutResponseEntity = restTemplate.postForEntity("http://localhost:8080/api/v1/workouts", workoutDTO, WorkoutDTO.class);

        WorkoutDTO createdWorkout = workoutResponseEntity.getBody();
        assertNotNull(createdWorkout);

        restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/assign-to-workout", AssignExerciseToWorkoutDTO.builder()
                .exerciseId(createdCondition.getId()).workoutId(createdWorkout.getId()).position(0).build(), List.class);

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.delete(URL_PREFIX + "/" + createdCondition.getId()));
        assertEquals("400 : \"{\"errorMessage\":\"Unable to finish an operation due to existing dependencies.\",\"messageCode\":\"CASCADE_DEPENDENCIES\",\"parentEntityId\":3,\"operation\":null,\"dependencies\":[{\"type\":\"WORKOUT\",\"ids\":[4]}]}\"", exception.getMessage());

    }

    @NotNull
    private Map<String, DataChange> getChanges() {
        return Map.of(
                "timeMin", DataChange.builder().operation("delete").build(),
                "timeSec", DataChange.builder().operation("delete").build(),
                "exercisePrescription", DataChange.builder().operation("delete").build()
        );
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
