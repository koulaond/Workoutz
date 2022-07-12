package com.ondrejkoula.service.plan;

import com.ondrejkoula.domain.plan.Plan;
import com.ondrejkoula.domain.plan.WorkoutToPlanAssignment;
import com.ondrejkoula.domain.plan.WorkoutToPlanAssignmentId;
import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.exception.OutOfTimeWindowException;
import com.ondrejkoula.repository.plan.PlanRepository;
import com.ondrejkoula.repository.plan.WorkoutToPlanAssignmentRepository;
import com.ondrejkoula.repository.workout.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class WorkoutToPlanAssignmentService {

    private final WorkoutRepository workoutRepository;

    private final PlanRepository planRepository;

    private final WorkoutToPlanAssignmentRepository assignmentRepository;

    @Autowired
    public WorkoutToPlanAssignmentService(WorkoutRepository workoutRepository,
                                          PlanRepository planRepository,
                                          WorkoutToPlanAssignmentRepository assignmentRepository) {
        this.workoutRepository = workoutRepository;
        this.planRepository = planRepository;
        this.assignmentRepository = assignmentRepository;
    }


    public void assignWorkoutToPlan(Long workoutId, Long planId, LocalDateTime dateAndTimeScheduled) {
        Workout workout = getWorkoutOrThrowException(workoutId);
        Plan plan = getPlanOrThrowException(planId);

        validateAssignedTimeIsWithinPlanWindow(plan, dateAndTimeScheduled);

        assignmentRepository.save(WorkoutToPlanAssignment.builder()
                .pk(WorkoutToPlanAssignmentId.builder().plan(plan).workout(workout).build())
                .dateAndTimeScheduled(dateAndTimeScheduled)
                .build());
    }

    public List<WorkoutToPlanAssignment> getAssignedWorkoutsToPlan(Long planId) {
        return assignmentRepository.getWorkoutsAssignedToPlan(planId);
    }

    private Workout getWorkoutOrThrowException(Long workoutId) {
        return workoutRepository.findById(workoutId)
                .orElseThrow(() -> new DataNotFoundException("Workout not found", "notFound"));
    }

    private Plan getPlanOrThrowException(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> new DataNotFoundException("Plan not found", "notFound"));
    }

    private void validateAssignedTimeIsWithinPlanWindow(Plan plan, LocalDateTime dateAndTimeScheduled) {
        if (dateAndTimeScheduled.isBefore(plan.getExpectedPlanStart())) {
            throw OutOfTimeWindowException.before(dateAndTimeScheduled, plan.getExpectedPlanStart());
        }
        if (dateAndTimeScheduled.isAfter(plan.getExpectedPlanEnd())) {
            throw OutOfTimeWindowException.after(dateAndTimeScheduled, plan.getExpectedPlanEnd());
        }
    }

}
