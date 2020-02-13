package com.ondrejkoula.domain;

import com.ondrejkoula.TrainingPlanWorkoutID;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(
        name = "training_plan_workout",
        uniqueConstraints = @UniqueConstraint(columnNames = {"training_plan_id", "workout_id", "total_order"})
)
@AssociationOverrides({
        @AssociationOverride(name = "pk.trainingPlan",
                joinColumns = @JoinColumn(name = "training_plan_id")),
        @AssociationOverride(name = "pk.workout",
                joinColumns = @JoinColumn(name = "workout_id"))})
public class TrainingPlanWorkout {

    @EmbeddedId
    private TrainingPlanWorkoutID pk;

    @Column(name = "total_order")
    private Integer totalOrder;
    private Integer week;
    private Integer dayInWeek;
    private Integer phase;
}
