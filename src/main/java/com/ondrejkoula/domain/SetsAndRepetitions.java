package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "sets_and_repetitions")
public class SetsAndRepetitions extends DomainEntity {

    @ManyToOne
    private ExercisePrescription exercisePrescription;

    private ExerciseValue<Integer> seriesCount;

    private ExerciseValue<Integer> repetitionsCount;

    private ExerciseValue<Integer> weight;

    private Integer maxTimeSec;

    private Integer maxTimeMin;
}

