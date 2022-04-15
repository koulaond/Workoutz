package com.ondrejkoula.domain.exercise;

import com.ondrejkoula.domain.DomainEntity;
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

        String exPrescLoggableString = "null";
        if (!Objects.isNull(exercisePrescription)) {
            exPrescLoggableString = exercisePrescription.loggableString();
        }
        return "High-intensity interval exercise: [intervals count: " + intervalsCount + ", intensity time: "
                + intensityIntervalTime + ", calm time: " + calmIntervalTime + ", prescription details: " + exPrescLoggableString + "]";
    }
}
