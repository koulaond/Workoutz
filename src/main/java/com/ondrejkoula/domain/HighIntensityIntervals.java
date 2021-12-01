package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "high_intensity_intervals")
public class HighIntensityIntervals extends DomainEntity {

    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    // Intervals count
    private Integer intervalsCount;

    private Integer intervalsCountGoal;

    // Interval times
    private Integer intensityIntervalTime;

    private Integer calmIntervalTime;
}
