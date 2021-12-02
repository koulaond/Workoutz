package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.ExercisePrescription;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Embedded exercise in circle set. DOes not exist as standalone exercise definition.
 */
@Getter
@Setter
@Entity
@Table(name = "circle_set_exercises")
public class CircleSetExercise extends DomainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    private Integer timeOverridenSec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circle_set_id")
    private CircleSet circleSet;

}
