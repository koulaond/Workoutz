package com.ondrejkoula.repository.workout;

import com.ondrejkoula.PersistenceTest;
import com.ondrejkoula.domain.exercise.weights.Weights;
import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.domain.workout.ExerciseToWorkoutAssignment;
import com.ondrejkoula.domain.workout.WorkoutExerciseId;
import com.ondrejkoula.repository.exercise.WeightsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkoutRepositoryTest extends PersistenceTest {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    WeightsRepository weightsRepository;

    @Autowired
    WorkoutExerciseRepository workoutExerciseRepository;

    @Test
    void shouldAssignExerciseToWorkoutAndReturnListWithOneElement() {
        Workout workout = Workout.builder().id(1L).build();
        workoutRepository.save(workout);

        Weights weights = Weights.builder().id(2L).build();
        weightsRepository.save(weights);

        workoutExerciseRepository.save(ExerciseToWorkoutAssignment.builder()
                .pk(WorkoutExerciseId.builder()
                        .workout(workout)
                        .exercise(weights)
                        .build())
                .position(0)
                .build());

        List<ExerciseToWorkoutAssignment> filtered = workoutExerciseRepository.getWorkoutsForExercise(weights.getId());

        assertThat(filtered).hasSize(1).satisfies(list ->{
            ExerciseToWorkoutAssignment exercise = list.iterator().next();
            assertThat(exercise.getPk().getExercise()).isEqualTo(weights);
            assertThat(exercise.getPk().getWorkout()).isEqualTo(workout);
        });
    }
}
