package com.ondrejkoula.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExercisesForWorkoutDTO {

    private Long workoutId;

    private Integer exercisesCount;

    private List<WorkoutExerciseListItem> exercises;
}
