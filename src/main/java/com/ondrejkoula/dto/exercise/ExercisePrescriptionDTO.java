package com.ondrejkoula.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.dto.AbstractDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExercisePrescriptionDTO extends AbstractDTO {

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

    @Override
    public ExercisePrescription toDomain() {
        return ExercisePrescription.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .label(getLabel())
                .description(getDescription())
                .exerciseType(getExerciseType()!= null ? getExerciseType().toDomain() : null)
                .build();
    }
}
