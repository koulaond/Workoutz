package com.ondrejkoula.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.SetsAndRepetitions;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SetsAndRepetitionsDTO extends ExerciseDTO {

    private ExercisePrescriptionDTO exercisePrescription;

    // Series values
    private Integer seriesCount;

    private Integer seriesCountGoal;


    // Repetitions values
    private Integer repetitionsCount;

    private Integer repetitionsCountGoal;


    // Weight values
    private Integer weight;

    private Integer weightGoal;


    // Time values
    private Integer maxTimeSec;

    private Integer maxTimeMin;

    @Builder
    public SetsAndRepetitionsDTO(Long id, String status, String note, ExercisePrescriptionDTO exercisePrescription,
                                 Integer seriesCount, Integer seriesCountGoal, Integer repetitionsCount, Integer repetitionsCountGoal,
                                 Integer weight, Integer weightGoal, Integer maxTimeSec, Integer maxTimeMin) {
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
    public SetsAndRepetitions toDomain() {
        return SetsAndRepetitions.builder()
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
                .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDomain() : null)
                .build();
    }
}
