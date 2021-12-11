package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "sets_and_repetitions")
public class SetsAndRepetitions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    protected String status;

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
}

