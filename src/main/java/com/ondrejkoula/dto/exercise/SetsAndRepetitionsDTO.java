package com.ondrejkoula.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.SetsAndRepetitions;
import lombok.*;

import javax.validation.constraints.NotNull;

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

    public static SetsAndRepetitionsDTO from(@NotNull SetsAndRepetitions setsAndRepetitions) {
        SetsAndRepetitionsDTOBuilder builder = SetsAndRepetitionsDTO.builder()
                .seriesCount(setsAndRepetitions.getSeriesCount())
                .seriesCountGoal(setsAndRepetitions.getSeriesCountGoal())
                .repetitionsCount(setsAndRepetitions.getRepetitionsCount())
                .repetitionsCountGoal(setsAndRepetitions.getRepetitionsCountGoal())
                .weight(setsAndRepetitions.getWeight())
                .weightGoal(setsAndRepetitions.getWeightGoal())
                .maxTimeSec(setsAndRepetitions.getMaxTimeSec())
                .maxTimeMin(setsAndRepetitions.getMaxTimeMin());

        if (setsAndRepetitions.getExercisePrescription() != null) {
            builder.exercisePrescription(ExercisePrescriptionDTO.from(setsAndRepetitions.getExercisePrescription()));
        }
        return builder.build();
    }
}
