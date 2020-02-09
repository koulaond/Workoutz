package com.ondrejkoula.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class WorkoutCreateDto {
    private String name;
    private String description;
    private String note;

    @JsonProperty("exercises")
    private Set<WorkoutExerciseUnitCreateDto> exerciseUnits;
}
