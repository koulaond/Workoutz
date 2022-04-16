package com.ondrejkoula.repository.workout;

import com.ondrejkoula.domain.exercise.SetsAndRepetitions;
import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.domain.workout.WorkoutExercise;
import com.ondrejkoula.domain.workout.WorkoutExerciseId;
import com.ondrejkoula.repository.exercise.SetsAndRepetitionsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkoutRepositoryIT {

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

        List<WorkoutExercise> filtered = workoutExerciseRepository.getWorkflowsForExercise(setsAndRepetitions.getId());

        assertThat(filtered).hasSize(1).satisfies(list ->{
            WorkoutExercise exercise = list.iterator().next();
            assertThat(exercise.getPk().getExercise()).isEqualTo(setsAndRepetitions);
            assertThat(exercise.getPk().getWorkout()).isEqualTo(workout);
        });
    }
}
