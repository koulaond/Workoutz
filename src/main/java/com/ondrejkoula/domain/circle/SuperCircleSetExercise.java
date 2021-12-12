package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.ExercisePrescription;
import lombok.*;

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
public class SuperCircleSetExercise extends DomainEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    @Column(name = "time_overriden_sec")
    private Integer timeOverriddenSec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_circle_set_id")
    private SuperCircleSet superCircleSet;

    @Column(name = "order_in_set")
    private Integer orderInSet;

    @Builder
    public SuperCircleSetExercise(Long id, String status, String note, ExercisePrescription exercisePrescription,
                                  Integer timeOverriddenSec, SuperCircleSet superCircleSet, Integer orderInSet) {
        super(id, status, note);
        this.exercisePrescription = exercisePrescription;
        this.timeOverriddenSec = timeOverriddenSec;
        this.superCircleSet = superCircleSet;
        this.orderInSet = orderInSet;
    }

    @Override
    public String loggableString() {

        String exPrescLoggableString = "null";
        if (!Objects.isNull(exercisePrescription)) {
            exPrescLoggableString = exercisePrescription.loggableString();
        }

        return "Circle set exercise: [time overriden (sec): " +
                timeOverriddenSec +
                ", order in set: " +
                orderInSet +
                ", prescription details: " +
                exPrescLoggableString + "]";
    }
}
