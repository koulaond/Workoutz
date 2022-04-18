package com.ondrejkoula.dto.exercise.circle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.dto.AbstractDTO;
import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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

    public static SuperCircleSetExerciseDTO from(@NotNull SuperCircleSetExercise superCircleSetExercise) {
        SuperCircleSetExerciseDTOBuilder builder = SuperCircleSetExerciseDTO.builder()
                .id(superCircleSetExercise.getId())
                .status(superCircleSetExercise.getStatus())
                .note(superCircleSetExercise.getNote())
                .timeOverriddenSec(superCircleSetExercise.getTimeOverriddenSec());

        if (superCircleSetExercise.getExercisePrescription() != null) {
            builder.exercisePrescription(ExercisePrescriptionDTO.from(superCircleSetExercise.getExercisePrescription()));
        }
        return builder.build();
    }
}
