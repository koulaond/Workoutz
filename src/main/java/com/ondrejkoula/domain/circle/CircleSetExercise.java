package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.ExercisePrescription;
import lombok.Data;

/**
 * Embedded exercise in circle set. DOes not exist as standalone exercise definition.
 */
@Data
public class CircleSetExercise {

    private ExercisePrescription exercisePrescription;

    private Integer timeOverridenSec;
}
