package com.ondrejkoula.dto.create;

import com.ondrejkoula.dto.preset.ExercisePresetDto;
import lombok.Data;

@Data
public class WorkoutExerciseUnitDto {

    private Long workoutId;
    private Integer position;
    private ExercisePresetDto exercisePresetDto;
}
