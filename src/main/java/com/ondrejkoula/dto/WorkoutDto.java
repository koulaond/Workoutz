package com.ondrejkoula.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ondrejkoula.dto.create.WorkoutExerciseUnitDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WorkoutDto {
    private String name;
    private String description;
    private String note;

    @JsonProperty("exercises")
    private List<WorkoutExerciseUnitDto> exerciseUnits;
}
