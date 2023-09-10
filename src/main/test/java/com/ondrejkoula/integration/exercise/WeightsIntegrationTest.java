package com.ondrejkoula.integration.exercise;

import com.ondrejkoula.dto.datachange.DataChange;
import com.ondrejkoula.dto.datachange.DataChanges;
import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.dto.exercise.weights.SingleSetDTO;
import com.ondrejkoula.dto.exercise.weights.WeightsDTO;
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

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class WeightsIntegrationTest extends IntegrationTest {

    static final String URL_PREFIX = "http://localhost:8080/api/v1/exercises/weights";

    RestTemplate restTemplate = new RestTemplate();
    
    @Test
    void create_whenAllRequiredFieldsAreSet_thenCreateWeights() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);

        WeightsDTO weightsToCreate = WeightsDTO.builder()
                .sets(singletonList(SingleSetDTO.builder().weight(1).position(2).repetitions(3).build()))
                .exercisePrescription(createdExercisePrescription)
                .maxTimeMin(30)
                .maxTimeSec(30)
                .build();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL_PREFIX, weightsToCreate, String.class);

        assertEquals("{\"id\":3,\"status\":null,\"note\":null,\"exercisePrescription\":{\"id\":2,\"status\":null,\"note\":null,\"label\":\"label\",\"exerciseType\":{\"id\":1,\"status\":null,\"note\":null,\"value\":\"exerciseType\",\"category\":\"typeCategory\"},\"description\":null},\"sets\":[{\"position\":2,\"repetitions\":3,\"repetitionsGoal\":null,\"weight\":1,\"weightGoal\":null}],\"maxTimeSec\":30,\"maxTimeMin\":30}", responseEntity.getBody());
    }

    @Test
    void create_whenAllRequiredFieldsMissing_thenReturnErrorMessage() {
        WeightsDTO weightsToCreate = WeightsDTO.builder().build();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(URL_PREFIX, weightsToCreate, WeightsDTO.class));
        assertEquals("400 : \"{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"maxTimeMin\":\"MISSING_FIELD_CONTENT\",\"maxTimeSec\":\"MISSING_FIELD_CONTENT\",\"exercisePrescription\":\"MISSING_REFERENCE\"}}\"", exception.getMessage());
    }

    @Test
    void update_whenAllRequiredFieldsAreSetOrUndeleted_thenUpdateWeights() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);
        ExercisePrescriptionDTO newExercisePrescription = createExercisePrescription(createdExerciseType);

        WeightsDTO weightsToCreate = WeightsDTO.builder().exercisePrescription(createdExercisePrescription).maxTimeMin(30).maxTimeSec(30).build();

        ResponseEntity<WeightsDTO> responseEntity = restTemplate.postForEntity(URL_PREFIX, weightsToCreate, WeightsDTO.class);
        WeightsDTO createdWeights = responseEntity.getBody();

        assertNotNull(createdWeights);

        Map<String, DataChange> changesMap = Map.of(
                "maxTimeMin", DataChange.builder().operation("update").value(31).build(),
                "exercisePrescription", DataChange.builder().operation("update").value(newExercisePrescription.getId()).build()
        );
        DataChanges dataChanges = DataChanges.builder()
                .changes(changesMap)
                .build();
        restTemplate.put(URL_PREFIX + "/" + createdWeights.getId(), dataChanges, WeightsDTO.class);
    }

    @Test
    void update_whenSomeRequiredValueIsDeleted_thenReturnErrorMessage() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);

        WeightsDTO weightsToCreate = WeightsDTO.builder().exercisePrescription(createdExercisePrescription).maxTimeMin(30).maxTimeSec(30).build();

        ResponseEntity<WeightsDTO> responseEntity = restTemplate.postForEntity(URL_PREFIX, weightsToCreate, WeightsDTO.class);
        WeightsDTO createdWeights = responseEntity.getBody();

        assertNotNull(createdWeights);

        DataChanges dataChanges = DataChanges.builder().changes(getChanges()).build();
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.put(URL_PREFIX + "/" + createdWeights.getId(), dataChanges, DataChanges.class));
        assertEquals("400 : \"{\"errorMessage\":\"Required data is missing on save.\",\"messageCode\":\"MISSING_DATA_ON_SAVE\",\"errorDetails\":{\"maxTimeMin\":\"MISSING_FIELD_CONTENT\",\"exercisePrescription\":\"MISSING_REFERENCE\"}}\"", exception.getMessage());
    }

    @Test
    void delete_successfulCase() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);

        WeightsDTO weightsToCreate = WeightsDTO.builder().exercisePrescription(createdExercisePrescription).maxTimeMin(30).maxTimeSec(30).build();

        ResponseEntity<WeightsDTO> responseEntity = restTemplate.postForEntity(URL_PREFIX, weightsToCreate, WeightsDTO.class);
        WeightsDTO createdWeights = responseEntity.getBody();

        assertNotNull(createdWeights);

        restTemplate.delete(URL_PREFIX + "/" + createdWeights.getId());

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.getForEntity(URL_PREFIX + "/" + createdWeights.getId(), WeightsDTO.class));
        assertEquals("404 : \"{\"errorMessage\":\"Data not found\",\"messageCode\":\"NOT_FOUND\"}\"", exception.getMessage());
    }

    @Test
    void delete_cannotDeleteDueToExistingDependencies() {
        ExerciseTypeDTO createdExerciseType = createExerciseType();
        ExercisePrescriptionDTO createdExercisePrescription = createExercisePrescription(createdExerciseType);

        WeightsDTO weightsToCreate = WeightsDTO.builder().exercisePrescription(createdExercisePrescription).maxTimeMin(30).maxTimeSec(30).build();

        ResponseEntity<WeightsDTO> responseEntity = restTemplate.postForEntity(URL_PREFIX, weightsToCreate, WeightsDTO.class);
        WeightsDTO createdWeights = responseEntity.getBody();

        assertNotNull(createdWeights);

        WorkoutDTO workoutDTO = WorkoutDTO.builder().label("workout").build();
        ResponseEntity<WorkoutDTO> workoutResponseEntity = restTemplate.postForEntity("http://localhost:8080/api/v1/workouts", workoutDTO, WorkoutDTO.class);

        WorkoutDTO createdWorkout = workoutResponseEntity.getBody();
        assertNotNull(createdWorkout);

        restTemplate.postForEntity("http://localhost:8080/api/v1/exercises/assign-to-workout", AssignExerciseToWorkoutDTO.builder()
                .exerciseId(createdWeights.getId()).workoutId(createdWorkout.getId()).position(0).build(), List.class);

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> restTemplate.delete(URL_PREFIX + "/" + createdWeights.getId()));
        assertEquals("400 : \"{\"errorMessage\":\"Unable to finish an operation due to existing dependencies.\",\"messageCode\":\"CASCADE_DEPENDENCIES\",\"parentEntityId\":3,\"operation\":null,\"dependencies\":[{\"type\":\"WORKOUT\",\"ids\":[4]}]}\"", exception.getMessage());

    }

    @NotNull
    private Map<String, DataChange> getChanges() {
        return Map.of(
                "maxTimeMin", DataChange.builder().operation("delete").build(),
                "maxTimeMax", DataChange.builder().operation("delete").build(),
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
