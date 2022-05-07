package com.ondrejkoula.dto.exercise.circle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.dto.AbstractDTO;
import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperCircleSetExerciseDTO extends AbstractDTO {

    private ExercisePrescriptionDTO exercisePrescription;

    private Integer timeOverriddenSec;

    @Builder
    public SuperCircleSetExerciseDTO(Long id, String status, String note,
                                     ExercisePrescriptionDTO exercisePrescription, Integer timeOverriddenSec) {
        super(id, status, note);
        this.exercisePrescription = exercisePrescription;
        this.timeOverriddenSec = timeOverriddenSec;
    }

    @Override
    public SuperCircleSetExercise toDomain() {
        return SuperCircleSetExercise.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .timeOverriddenSec(getTimeOverriddenSec())
                .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDomain() : null)
                .build();
    }
}
