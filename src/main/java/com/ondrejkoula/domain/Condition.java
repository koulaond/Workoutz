package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@Table(name = "condition")
public class Condition extends DomainEntity {

    private ExercisePrescription exercisePrescription;

    private Integer timeSec;

    private Integer timeMin;
}
