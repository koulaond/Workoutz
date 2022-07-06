package com.ondrejkoula.dto.workout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignExerciseToWorkoutDTO {


    private Long workoutId;

    private Long exerciseId;

    private Integer position;
}
