package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@Table(name = "high_intensity_intervals")
public class HighIntensityIntervals extends DomainEntity{

    private ExercisePrescription exercisePrescription;

    private ExerciseValue<Integer> intervalsCount;

    private Integer intensityIntervalTime;

    private Integer calmIntervalTime;
}
