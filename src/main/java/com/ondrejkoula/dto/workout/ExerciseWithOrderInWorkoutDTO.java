package com.ondrejkoula.dto.workout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.ExerciseWithOrderInWorkout;
import com.ondrejkoula.dto.ConvertibleFromDTOToDomain;
import com.ondrejkoula.dto.exercise.ExerciseDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseWithOrderInWorkoutDTO implements ConvertibleFromDTOToDomain {

    private ExerciseDTO exercise;

    private Integer position;

    @Builder
    public ExerciseWithOrderInWorkoutDTO(Integer position, ExerciseDTO exercise) {
        this.position = position;
        this.exercise = exercise;
    }

    @Override
    public ExerciseWithOrderInWorkout toDomain() {
        return ExerciseWithOrderInWorkout.builder()
                .exercise(exercise.toDomain())
                .position(position)
                .build();
    }
}
