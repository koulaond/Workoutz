package com.ondrejkoula.domain.superset;

import com.ondrejkoula.domain.ExercisePrescription;
import com.ondrejkoula.domain.ExerciseValue;
import lombok.Data;

/**
 * Embedded exercise in Super set series.
 */
@Data
public class SuperSetExercise {

    private ExercisePrescription exercisePrescription;

    private ExerciseValue<Integer> repetitionsCount;

    private ExerciseValue<Integer> weight;

    private Integer maxTimeSec;

    private Integer maxTimeMin;
}
