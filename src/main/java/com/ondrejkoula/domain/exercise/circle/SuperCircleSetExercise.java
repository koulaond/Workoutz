package com.ondrejkoula.domain.exercise.circle;

import com.ondrejkoula.domain.IncorporatedItem;
import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.dto.exercise.circle.SuperCircleSetExerciseDTO;
import com.ondrejkoula.service.validation.annotation.RequiredReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * Embedded exercise in circle set. DOes not exist as standalone exercise definition.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "super_circle_set_exercises")
public class SuperCircleSetExercise extends IncorporatedItem<SuperCircleSet> {


    @RequiredReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    @Column(name = "time_overriden_sec")
    private Integer timeOverriddenSec;

    @Builder
    public SuperCircleSetExercise(Long id, String status, String note, SuperCircleSet superCircleSet, Integer position,
                                  ExercisePrescription exercisePrescription, Integer timeOverriddenSec) {
        super(id, status, note, superCircleSet, position);
        this.exercisePrescription = exercisePrescription;
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
