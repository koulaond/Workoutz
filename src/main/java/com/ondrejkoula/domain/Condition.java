package com.ondrejkoula.domain;

import lombok.Data;

@Data
public class Condition extends DomainEntity {

    private ExercisePrescription exercisePrescription;

    private Integer timeSec;

    private Integer timeMin;
}
