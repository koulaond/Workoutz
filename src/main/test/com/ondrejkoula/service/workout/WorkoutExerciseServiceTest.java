package com.ondrejkoula.service.workout;

import com.ondrejkoula.PersistenceTest;
import com.ondrejkoula.domain.exercise.ExerciseWithOrderInWorkout;
import com.ondrejkoula.domain.exercise.SetsAndRepetitions;
import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.repository.exercise.NonSpecificExerciseRepository;
import com.ondrejkoula.repository.exercise.SetsAndRepetitionsRepository;
import com.ondrejkoula.repository.workout.WorkoutExerciseRepository;
import com.ondrejkoula.repository.workout.WorkoutRepository;
import com.ondrejkoula.service.exercise.WorkoutExercisesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WorkoutExerciseServiceTest extends PersistenceTest {

    WorkoutService workoutService;

    WorkoutExercisesService workoutExercisesService;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    NonSpecificExerciseRepository exerciseRepository;

    @Autowired
    SetsAndRepetitionsRepository setsAndRepetitionsRepository;

    @BeforeEach
    void setup() {
        workoutService = new WorkoutService(workoutRepository, workoutExerciseRepository);
        workoutExercisesService = new WorkoutExercisesService(exerciseRepository, workoutRepository, workoutExerciseRepository);
    }

    @Test
    void shouldAssignExercisesToWorkout() {
        SetsAndRepetitions ex1 = setsAndRepetitionsRepository.save(SetsAndRepetitions.builder().note("ex1").build());
        SetsAndRepetitions ex2 = setsAndRepetitionsRepository.save(SetsAndRepetitions.builder().note("ex2").build());

        Workout saved = workoutRepository.save(Workout.builder().id(10L).build());

        workoutExercisesService.assignExerciseToWorkout(saved.getId(), ex1.getId(), 0);
        workoutExercisesService.assignExerciseToWorkout(saved.getId(), ex2.getId(), 0);

        Workout found = workoutService.findById(saved.getId());
        List<ExerciseWithOrderInWorkout> exercisesForWorkout = workoutExercisesService.getExercisesForWorkout(found.getId());

        assertThat(exercisesForWorkout).hasSize(2)
                .satisfies(exercises -> {
                    assertThat(exercises.get(0)).satisfies(exercise -> {
                        assertThat(exercise.getPosition()).isEqualTo(0);
                        assertThat(exercise.getExercise().getId()).isEqualTo(ex2.getId());
                    });
                    assertThat(exercises.get(1)).satisfies(exercise -> {
                        assertThat(exercise.getPosition()).isEqualTo(1);
                        assertThat(exercise.getExercise().getId()).isEqualTo(ex1.getId());
                    });
                });
    }
}