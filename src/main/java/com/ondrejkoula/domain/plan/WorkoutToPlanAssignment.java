package com.ondrejkoula.domain.plan;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "workouts_plans_assignments")
@AssociationOverrides({
        @AssociationOverride(name = "pk.workout",
                joinColumns = @JoinColumn(name = "workout_id")),
        @AssociationOverride(name = "pk.plan",
                joinColumns = @JoinColumn(name = "plan_id"))
})
public class WorkoutToPlanAssignment {

    @EmbeddedId
    private WorkoutToPlanAssignmentId pk;

    @Column(name = "date_and_time_scheduled")
    private LocalDateTime dateAndTimeScheduled;

    @Builder
    public WorkoutToPlanAssignment(WorkoutToPlanAssignmentId pk, LocalDateTime dateAndTimeScheduled) {
        this.pk = pk;
        this.dateAndTimeScheduled = dateAndTimeScheduled;
    }
}
