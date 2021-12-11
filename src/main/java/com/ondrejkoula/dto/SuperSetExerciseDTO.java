package com.ondrejkoula.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuperSetExerciseDTO {

    private long id;

    private String status;

    private String note;

    private ExercisePrescriptionDTO exercisePrescription;

    private Integer repetitionsCount;

    private Integer repetitionsCountGoal;

    private Integer weight;

    private Integer weightGoal;

    private Integer maxTimeSec;

    private Integer maxTimeMin;

    private Integer position;
}
