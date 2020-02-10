package com.ondrejkoula.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ondrejkoula.dto.create.WorkoutExerciseUnitDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDto {
    private Long id;
    private String name;
    private String description;
    private String note;

    @JsonProperty("exercises")
    private List<WorkoutExerciseUnitDto> exerciseUnits;
}
