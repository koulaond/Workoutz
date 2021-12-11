package com.ondrejkoula.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExercisePrescriptionDTO {

    private long id;

    private String status;

    private String label;

    private ExerciseTypeDTO exerciseType;

    private String description;
}
