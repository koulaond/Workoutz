package com.ondrejkoula.dto.plan;

import com.ondrejkoula.domain.plan.WorkoutToPlanAssignment;
import com.ondrejkoula.domain.plan.WorkoutToPlanAssignmentId;
import com.ondrejkoula.dto.ConvertibleFromDTOToDomain;
import com.ondrejkoula.dto.workout.WorkoutDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class WorkoutToPlanAssignmentDTO implements ConvertibleFromDTOToDomain {

    private WorkoutDTO workout;

    private PlanDTO plan;

    private LocalDateTime dateAndTimeScheduled;

    @Override
    public WorkoutToPlanAssignment toDomain() {
        return WorkoutToPlanAssignment.builder()
                .pk(WorkoutToPlanAssignmentId.builder()
                        .workout(workout.toDomain())
                        .plan(plan.toDomain())
                        .build())
                .dateAndTimeScheduled(dateAndTimeScheduled)
                .build();
    }
}
