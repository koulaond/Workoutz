package com.ondrejkoula.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ondrejkoula.dto.preset.ExercisePresetDto;
import lombok.Data;

@Data
public class WorkoutExerciseUnitDto {

    private Long workoutId;
    private Integer position;

    @JsonProperty("exercisePreset")
    private ExercisePresetDto exercisePresetDto;
}
