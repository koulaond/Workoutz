package com.ondrejkoula.repository.workout;

import com.ondrejkoula.PersistenceTest;
import com.ondrejkoula.domain.exercise.SetsAndRepetitions;
import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.domain.workout.WorkoutExercise;
import com.ondrejkoula.domain.workout.WorkoutExerciseId;
import com.ondrejkoula.repository.exercise.SetsAndRepetitionsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkoutRepositoryTest extends PersistenceTest {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    SetsAndRepetitionsRepository setsAndRepetitionsRepository;

    @Autowired
    WorkoutExerciseRepository workoutExerciseRepository;

    @Test
    void shouldAssignExerciseToWorkoutAndReturnListWithOneElement() {
        Workout workout = Workout.builder().id(1L).build();
        workoutRepository.save(workout);

        SetsAndRepetitions setsAndRepetitions = SetsAndRepetitions.builder().id(2L).build();
        setsAndRepetitionsRepository.save(setsAndRepetitions);

        workoutExerciseRepository.save(WorkoutExercise.builder()
                .pk(WorkoutExerciseId.builder()
                        .workout(workout)
                        .exercise(setsAndRepetitions)
                        .build())
                .position(0)
                .build());

        List<WorkoutExercise> filtered = workoutExerciseRepository.getWorkoutsForExercise(setsAndRepetitions.getId());

        assertThat(filtered).hasSize(1).satisfies(list ->{
            WorkoutExercise exercise = list.iterator().next();
            assertThat(exercise.getPk().getExercise()).isEqualTo(setsAndRepetitions);
            assertThat(exercise.getPk().getWorkout()).isEqualTo(workout);
        });
    }
}
