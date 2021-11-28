package com.ondrejkoula.domain.superset;

import com.ondrejkoula.domain.ExercisePrescription;
import com.ondrejkoula.domain.ExerciseValue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Embedded exercise in Super set series.
 */
@Getter
@Setter
@Table(name = "super_set_exercise")
public class SuperSetExercise {

    @ManyToOne
    @JoinColumn(name = "super_set_id")
    private SuperSet superSet;

    @ManyToOne
    private ExercisePrescription exercisePrescription;

    private ExerciseValue<Integer> repetitionsCount;

    private ExerciseValue<Integer> weight;

    private Integer maxTimeSec;

    private Integer maxTimeMin;
}
