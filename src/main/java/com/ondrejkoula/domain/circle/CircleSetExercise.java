package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.ExercisePrescription;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Embedded exercise in circle set. DOes not exist as standalone exercise definition.
 */
@Getter
@Setter
@Table(name = "circle_set_exercise")
public class CircleSetExercise extends DomainEntity {

    private ExercisePrescription exercisePrescription;

    private Integer timeOverridenSec;

    @ManyToOne
    @JoinColumn(name = "circle_set_id")
    private CircleSet circleSet;

}
