package com.ondrejkoula.domain.plan;

import com.ondrejkoula.domain.ConvertibleFromDomainToDTO;
import com.ondrejkoula.dto.plan.WorkoutToPlanAssignmentDTO;
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
public class WorkoutToPlanAssignment implements ConvertibleFromDomainToDTO {

    @EmbeddedId
    private WorkoutToPlanAssignmentId pk;

    @Column(name = "date_and_time_scheduled")
    private LocalDateTime dateAndTimeScheduled;

    @Builder
    public WorkoutToPlanAssignment(WorkoutToPlanAssignmentId pk, LocalDateTime dateAndTimeScheduled) {
        this.pk = pk;
        this.dateAndTimeScheduled = dateAndTimeScheduled;
    }

    @Override
    public WorkoutToPlanAssignmentDTO toDTO() {
        return WorkoutToPlanAssignmentDTO.builder()
                .workout(pk.getWorkout().toDTO())
                .plan(pk.getPlan().toDTO())
                .dateAndTimeScheduled(dateAndTimeScheduled)
                .build();
    }
}
