package com.ondrejkoula.repository.plan;

import com.ondrejkoula.PersistenceTest;
import com.ondrejkoula.domain.plan.Plan;
import com.ondrejkoula.domain.plan.WorkoutToPlanAssignment;
import com.ondrejkoula.domain.plan.WorkoutToPlanAssignmentId;
import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.repository.workout.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WorkoutToPlanAssignmentRepositoryTest extends PersistenceTest {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    WorkoutToPlanAssignmentRepository assignmentRepository;

    @Test
    void shouldAssignMultipleWorkoutsToPlan_AndReturnChronologicallyOrderedList() {
        Plan plan = Plan.builder().title("plan").build();
        Workout workout1 = Workout.builder().label("workout1").build();
        Workout workout2 = Workout.builder().label("workout2").build();

        plan = planRepository.save(plan);
        workout1 = workoutRepository.save(workout1);
        workout2 = workoutRepository.save(workout2);

        assignmentRepository.save(WorkoutToPlanAssignment.builder()
                .pk(WorkoutToPlanAssignmentId.builder()
                        .workout(workout1)
                        .plan(plan)
                        .build())
                        .dateAndTimeScheduled(LocalDateTime.of(2022, 5, 1, 8, 40))
                .build());

        assignmentRepository.save(WorkoutToPlanAssignment.builder()
                .pk(WorkoutToPlanAssignmentId.builder()
                        .workout(workout2)
                        .plan(plan)
                        .build())
                .dateAndTimeScheduled(LocalDateTime.of(2022, 5, 1, 9, 40))
                .build());

        List<WorkoutToPlanAssignment> workoutsAssignedToPlan = assignmentRepository.getWorkoutsAssignedToPlan(plan.getId());

        assertThat(workoutsAssignedToPlan).hasSize(2)
                .satisfies(assignments -> {
                    WorkoutToPlanAssignment first = assignments.get(0);
                    WorkoutToPlanAssignment second = assignments.get(1);

                    assertThat(second.getDateAndTimeScheduled()).isAfter(first.getDateAndTimeScheduled());
                });
    }
}