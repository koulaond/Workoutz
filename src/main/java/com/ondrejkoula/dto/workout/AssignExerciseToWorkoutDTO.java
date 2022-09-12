package com.ondrejkoula.dto.workout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignExerciseToWorkoutDTO {

    private Long workoutId;

    private Long exerciseId;

    private Integer position;

    @Builder
    public AssignExerciseToWorkoutDTO(Long workoutId, Long exerciseId, Integer position) {
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.position = position;
    }
}
