package com.ondrejkoula.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseTypeDTO {

    private long id;

    private String status;

    private String type;

    private String category;
}
