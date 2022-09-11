package com.ondrejkoula.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.HighIntensityInterval;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HighIntensityIntervalDTO extends ExerciseDTO {

    private ExercisePrescriptionDTO exercisePrescription;

    // Intervals count
    private Integer intervalsCount;

    private Integer intervalsCountGoal;

    // Interval times
    private Integer intensityIntervalTime;

    private Integer calmIntervalTime;

    @Builder
    public HighIntensityIntervalDTO(Long id, String status, String note, ExercisePrescriptionDTO exercisePrescription,
                                    Integer intervalsCount, Integer intervalsCountGoal, Integer intensityIntervalTime,
                                    Integer calmIntervalTime) {
        super(id, status, note);
        this.exercisePrescription = exercisePrescription;
        this.intervalsCount = intervalsCount;
        this.intervalsCountGoal = intervalsCountGoal;
        this.intensityIntervalTime = intensityIntervalTime;
        this.calmIntervalTime = calmIntervalTime;
    }

    @Override
    public HighIntensityInterval toDomain() {
        return HighIntensityInterval.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .intervalsCount(getIntervalsCount())
                .intervalsCountGoal(getIntervalsCountGoal())
                .intensityIntervalTime(getIntensityIntervalTime())
                .calmIntervalTime(getCalmIntervalTime())
                .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDomain() : null)
                .build();
    }
}
