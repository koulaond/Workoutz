package com.ondrejkoula.dto.preset;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ondrejkoula.dto.ExerciseDto;
import lombok.Data;

import java.util.List;

@Data
@JsonTypeName("StandardSet")
public class StandardSetPresetDto extends ExercisePresetDto{

    @JsonProperty(value = "exercise")
    private ExerciseDto exerciseDto;

    private Integer series;

    @JsonProperty(value = "reps")
    private List<Integer> repsPerSeries;

}
