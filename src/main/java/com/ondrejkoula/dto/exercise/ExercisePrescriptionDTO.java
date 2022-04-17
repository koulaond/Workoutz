package com.ondrejkoula.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.ExercisePrescription;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExercisePrescriptionDTO extends ExerciseDTO {

    private String label;

    private ExerciseTypeDTO exerciseType;

    private String description;

    @Builder
    public ExercisePrescriptionDTO(Long id,
                                   String status,
                                   String note,
                                   String label,
                                   ExerciseTypeDTO exerciseType,
                                   String description) {
        super(id, status, note);
        this.label = label;
        this.exerciseType = exerciseType;
        this.description = description;
    }

    public static ExercisePrescriptionDTO from(@NotNull ExercisePrescription exercisePrescription) {
        ExercisePrescriptionDTOBuilder builder = ExercisePrescriptionDTO.builder()
                .id(exercisePrescription.getId())
                .status(exercisePrescription.getStatus())
                .note(exercisePrescription.getNote())
                .label(exercisePrescription.getLabel())
                .description(exercisePrescription.getDescription());

        if (exercisePrescription.getExerciseType() != null) {
            builder.exerciseType(ExerciseTypeDTO.from(exercisePrescription.getExerciseType()));
        }
        return builder
                .build();
    }
}
