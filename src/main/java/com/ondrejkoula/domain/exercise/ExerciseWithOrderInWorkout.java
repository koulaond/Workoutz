package com.ondrejkoula.domain.exercise;

import com.ondrejkoula.domain.workout.WorkoutExercise;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseWithOrderInWorkout {

    private Exercise exercise;

    private Integer position;

    public static ExerciseWithOrderInWorkout of(WorkoutExercise workoutExercise) {
        return ExerciseWithOrderInWorkout.builder()
                .exercise(workoutExercise.getPk().getExercise())
                .position(workoutExercise.getPosition())
                .build();
    }
}
