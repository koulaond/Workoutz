package com.ondrejkoula.dto.preset.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ondrejkoula.dto.preset.ExercisePresetDto;
import lombok.Data;

import java.util.List;

@Data
@JsonTypeName("SuperSet")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperSetPresetCreateDto extends ExercisePresetDto {

    private List<Long> exerciseIds;
    private Integer series;
    private List<List<Integer>> repsPerSeries;
}
