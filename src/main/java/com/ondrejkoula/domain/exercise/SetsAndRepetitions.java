package com.ondrejkoula.domain.exercise;

import com.ondrejkoula.dto.exercise.SetsAndRepetitionsDTO;
import com.ondrejkoula.service.validation.annotation.Required;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sets_and_repetitions")
public class SetsAndRepetitions extends Exercise {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    // Series values
    @Required
    @Column(name = "series_count")
    private Integer seriesCount;

    @Column(name = "series_count_goal")
    private Integer seriesCountGoal;

    // Repetitions values
    @Required
    @Column(name = "repetitions_count")
    private Integer repetitionsCount;

    @Column(name = "repetitions_count_goal")
    private Integer repetitionsCountGoal;


    // Weight values
    @Required
    @Column(name = "weight")
    private Integer weight;

    @Column(name = "weight_goal")
    private Integer weightGoal;

    // Time values
    @Required
    @Column(name = "max_time_sec")
    private Integer maxTimeSec;

    @Required
    @Column(name = "max_time_min")
    private Integer maxTimeMin;

    @Builder
    public SetsAndRepetitions(Long id, String status, String note, ExercisePrescription exercisePrescription,
                              Integer seriesCount, Integer seriesCountGoal, Integer repetitionsCount,
                              Integer repetitionsCountGoal, Integer weight, Integer weightGoal, Integer maxTimeSec, Integer maxTimeMin) {
        super(id, status, note);
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

    @Override
    public SetsAndRepetitionsDTO toDTO() {
        return SetsAndRepetitionsDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .seriesCount(getSeriesCount())
                .seriesCountGoal(getSeriesCountGoal())
                .repetitionsCount(getRepetitionsCount())
                .repetitionsCountGoal(getRepetitionsCountGoal())
                .weight(getWeight())
                .weightGoal(getWeightGoal())
                .maxTimeSec(getMaxTimeSec())
                .maxTimeMin(getMaxTimeMin())
                .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDTO() : null)
                .build();
    }
}

