package com.ondrejkoula.dto.exercise.weights;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateWeightsSetsDTO {

    private List<SingleSetDTO> sets;

}
