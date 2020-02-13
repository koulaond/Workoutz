package com.ondrejkoula.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class WorkoutCreateDto {
    protected String name;
    protected String description;
    protected String note;

    @JsonProperty("schema")
    private Set<WorkoutExerciseUnitCreateDto> exerciseUnits;
}
