package com.ondrejkoula.dto.preset;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ondrejkoula.dto.ExerciseDto;
import lombok.Data;

import java.util.List;

@Data
@JsonTypeName("SuperSet")
public class SuperSetPresetDto extends ExercisePresetDto {

    @JsonProperty(value = "exercises")
    private List<ExerciseDto> exerciseDtos;

    private Integer series;

    @JsonProperty(value = "reps")
    private List<List<Integer>> repsPerSeries;
}
