package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(
        name = "training_plan_workout",
        uniqueConstraints = @UniqueConstraint(columnNames = {"training_plan_id", "workout_id", "week", "day_in_week", "order_within_day", "phase"})
)
@AssociationOverrides({
        @AssociationOverride(name = "trainingPlan",
                joinColumns = @JoinColumn(name = "training_plan_id")),
        @AssociationOverride(name = "workout",
                joinColumns = @JoinColumn(name = "workout_id"))})
public class TrainingPlanWorkout extends AbstractEntity {

    @ManyToOne
    private TrainingPlan trainingPlan;

    @ManyToOne
    private Workout workout;

    private Integer week;

    @Column(name = "day_in_week")
    private Integer dayInWeek;

    @Column(name = "order_within_day")
    private Integer orderWithinDay;
    private Integer phase;
}
