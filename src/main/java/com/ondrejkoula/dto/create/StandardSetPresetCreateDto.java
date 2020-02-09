package com.ondrejkoula.dto.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ondrejkoula.dto.preset.ExercisePresetDto;
import lombok.Data;

import java.util.List;

@Data
@JsonTypeName("StandardSet")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StandardSetPresetCreateDto extends ExercisePresetDto {

    private Long id;
    private Long exerciseId;
    private Integer series;
    private List<Integer> repsPerSeries;
}
