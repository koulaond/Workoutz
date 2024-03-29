package com.ondrejkoula.domain.exercise;

import com.ondrejkoula.dto.exercise.HighIntensityIntervalDTO;
import com.ondrejkoula.service.measurements.ResultValue;
import com.ondrejkoula.service.validation.annotation.Required;
import com.ondrejkoula.service.validation.annotation.RequiredReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "high_intensity_intervals")
public class HighIntensityInterval extends Exercise {

    @RequiredReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    // Intervals count
    @Required
    @ResultValue(measurable = true, thresholdFieldName = "intervalsCountGoal")
    @Column(name = "intervals_count")
    private Integer intervalsCount;

    @Column(name = "intervals_count_goal")
    private Integer intervalsCountGoal;

    // Interval times
    @Required
    @Column(name = "intensity_interval_time")
    private Integer intensityIntervalTime;

    @Required
    @Column(name = "calm_interval_time")
    private Integer calmIntervalTime;

    @Builder
    public HighIntensityInterval(Long id, String status, String note, ExercisePrescription exercisePrescription,
                                 Integer intervalsCount, Integer intervalsCountGoal, Integer intensityIntervalTime, Integer calmIntervalTime) {
        super(id, status, note);
        this.exercisePrescription = exercisePrescription;
        this.intervalsCount = intervalsCount;
        this.intervalsCountGoal = intervalsCountGoal;
        this.intensityIntervalTime = intensityIntervalTime;
        this.calmIntervalTime = calmIntervalTime;
    }

    @Override
    public String loggableString() {
        String loggableString = "null";

        if (!Objects.isNull(exercisePrescription)) {
            loggableString = exercisePrescription.loggableString();
        }
        return "High-intensity interval exercise: [intervals count: " + intervalsCount + ", intensity time: "
                + intensityIntervalTime + ", calm time: " + calmIntervalTime + ", prescription details: " + loggableString + "]";
    }

    @Override
    public HighIntensityIntervalDTO toDTO() {
        return HighIntensityIntervalDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .intervalsCount(getIntervalsCount())
                .intervalsCountGoal(getIntervalsCountGoal())
                .intensityIntervalTime(getIntensityIntervalTime())
                .calmIntervalTime(getCalmIntervalTime())
                .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDTO() : null)
                .build();
    }
}
