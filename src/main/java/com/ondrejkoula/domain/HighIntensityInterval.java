package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "high_intensity_intervals")
public class HighIntensityInterval extends DomainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    // Intervals count
    private Integer intervalsCount;

    private Integer intervalsCountGoal;

    // Interval times
    private Integer intensityIntervalTime;

    private Integer calmIntervalTime;
}
