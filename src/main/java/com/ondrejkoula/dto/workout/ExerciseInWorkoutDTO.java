package com.ondrejkoula.dto.workout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.dto.exercise.ExerciseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseInWorkoutDTO {

    private Integer position;

    private ExerciseDTO exercise;
}
