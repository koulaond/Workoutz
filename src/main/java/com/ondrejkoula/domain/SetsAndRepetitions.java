package com.ondrejkoula.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sets_and_repetitions")
public class SetsAndRepetitions extends DomainEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    // Series values
    @Column(name = "series_count")
    private Integer seriesCount;

    @Column(name = "series_count_goal")
    private Integer seriesCountGoal;


    // Repetitions values
    @Column(name = "repetitions_count")
    private Integer repetitionsCount;

    @Column(name = "repetitions_count_goal")
    private Integer repetitionsCountGoal;


    // Weight values
    @Column(name = "weight")
    private Integer weight;

    @Column(name = "weight_goal")
    private Integer weightGoal;

    // Time values
    @Column(name = "max_time_sec")
    private Integer maxTimeSec;

    @Column(name = "max_time_min")
    private Integer maxTimeMin;

    @Builder
    public SetsAndRepetitions(ExercisePrescription exercisePrescription, Integer seriesCount, Integer seriesCountGoal, Integer repetitionsCount,
                              Integer repetitionsCountGoal, Integer weight, Integer weightGoal, Integer maxTimeSec, Integer maxTimeMin) {
        this.exercisePrescription = exercisePrescription;
        this.seriesCount = seriesCount;
        this.seriesCountGoal = seriesCountGoal;
        this.repetitionsCount = repetitionsCount;
        this.repetitionsCountGoal = repetitionsCountGoal;
        this.weight = weight;
        this.weightGoal = weightGoal;
        this.maxTimeSec = maxTimeSec;
        this.maxTimeMin = maxTimeMin;
    }

    @Override
    public String loggableString() {

        String exPrescLoggableString = "null";
        if (!Objects.isNull(exercisePrescription)) {
            exPrescLoggableString = exercisePrescription.loggableString();
        }

        return "Sets and repetitions exercise: [series count: " + seriesCount
                + ", reps count: " + repetitionsCount + ", prescription details : " + exPrescLoggableString + "]";
    }
}

