package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "conditions")
public class Condition extends DomainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    @Column(name = "time_sec")
    private Integer timeSec;

    @Column(name = "time_min")
    private Integer timeMin;
}
