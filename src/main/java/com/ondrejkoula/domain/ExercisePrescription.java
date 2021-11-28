package com.ondrejkoula.domain;

import lombok.Data;

@Data
public class ExercisePrescription extends DomainEntity {

    private String label;

    private ExerciseType exerciseType;

    private String description;
}
