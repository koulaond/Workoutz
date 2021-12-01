package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "condition")
public class Condition extends DomainEntity {

    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    @Column(name = "time_sec")
    private Integer timeSec;

    @Column(name = "time_min")
    private Integer timeMin;
}
