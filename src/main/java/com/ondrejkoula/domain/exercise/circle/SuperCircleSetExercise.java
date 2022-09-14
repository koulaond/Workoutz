package com.ondrejkoula.domain.exercise.circle;

import com.ondrejkoula.domain.CompositionChildExercise;
import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.dto.exercise.circle.SuperCircleSetExerciseDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Embedded exercise in circle set. DOes not exist as standalone exercise definition.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "super_circle_set_exercises")
public class SuperCircleSetExercise extends CompositionChildExercise<SuperCircleSet> {
    
    @Column(name = "time_overriden_sec")
    private Integer timeOverriddenSec;

    @Builder
    public SuperCircleSetExercise(Long id, String status, String note, ExercisePrescription exercisePrescription,
                                  SuperCircleSet superCircleSet, Integer position, Integer timeOverriddenSec) {
        super(id, status, note, exercisePrescription, superCircleSet, position);
        this.timeOverriddenSec = timeOverriddenSec;
    }

    @Override
    public String loggableString() {
        String exPrescLoggableString = "null";

        if (!Objects.isNull(exercisePrescription)) {
            exPrescLoggableString = exercisePrescription.loggableString();
        }

        return "Circle set exercise: [time overriden (sec): " +
                timeOverriddenSec +
                ", position: " +
                position +
                ", prescription details: " +
                exPrescLoggableString + "]";
    }

    @Override
    public SuperCircleSetExerciseDTO toDTO() {
        return SuperCircleSetExerciseDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .timeOverriddenSec(getTimeOverriddenSec())
                .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDTO() : null)
                .build();
    }
}
