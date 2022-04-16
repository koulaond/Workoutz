package com.ondrejkoula.service.workout;

import com.ondrejkoula.domain.exercise.SetsAndRepetitions;
import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.repository.ExerciseRepository;
import com.ondrejkoula.repository.exercise.SetsAndRepetitionsRepository;
import com.ondrejkoula.repository.workout.WorkoutExerciseRepository;
import com.ondrejkoula.repository.workout.WorkoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WorkoutServiceTest {

    WorkoutService workoutService;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    SetsAndRepetitionsRepository setsAndRepetitionsRepository;

    @BeforeEach
    void setup() {
        workoutService = new WorkoutService(workoutRepository, exerciseRepository, workoutExerciseRepository);
    }

    @Test
    void assignExerciseToWorkflow() {
        setsAndRepetitionsRepository.save(SetsAndRepetitions.builder().id(1L).build());
        setsAndRepetitionsRepository.save(SetsAndRepetitions.builder().id(2L).build());
        setsAndRepetitionsRepository.save(SetsAndRepetitions.builder().id(3L).build());

        Workout saved = workoutRepository.save(Workout.builder().id(10L).build());

        workoutService.assignExerciseToWorkflow(saved.getId(), 3L, 0);
        workoutService.assignExerciseToWorkflow(saved.getId(), 2L, 0);
        workoutService.assignExerciseToWorkflow(saved.getId(), 1L, 0);

        Workout found = workoutService.findById(saved.getId());
        System.out.println();
    }
}