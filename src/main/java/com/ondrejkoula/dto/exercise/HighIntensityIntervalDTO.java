package com.ondrejkoula.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.HighIntensityInterval;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
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

    public static HighIntensityIntervalDTO from(HighIntensityInterval highIntensityInterval) {
        HighIntensityIntervalDTOBuilder builder = HighIntensityIntervalDTO.builder()
                .id(highIntensityInterval.getId())
                .status(highIntensityInterval.getStatus())
                .note(highIntensityInterval.getNote())
                .intervalsCount(highIntensityInterval.getIntervalsCount())
                .intervalsCountGoal(highIntensityInterval.getIntervalsCountGoal())
                .intensityIntervalTime(highIntensityInterval.getIntensityIntervalTime())
                .calmIntervalTime(highIntensityInterval.getCalmIntervalTime());

        if (highIntensityInterval.getExercisePrescription() != null) {
            builder.exercisePrescription(ExercisePrescriptionDTO.from(highIntensityInterval.getExercisePrescription()));
        }

        return builder.build();
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
