package com.ondrejkoula.domain.exercise;

import com.ondrejkoula.domain.ConvertibleFromDomainToDTO;
import com.ondrejkoula.domain.workout.ExerciseToWorkoutAssignment;
import com.ondrejkoula.dto.workout.ExerciseWithOrderInWorkoutDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseWithOrderInWorkout implements ConvertibleFromDomainToDTO {

    private Exercise exercise;

    private Integer position;

    public static ExerciseWithOrderInWorkout of(ExerciseToWorkoutAssignment exerciseToWorkoutAssignment) {
        return ExerciseWithOrderInWorkout.builder()
                .exercise(exerciseToWorkoutAssignment.getPk().getExercise())
                .position(exerciseToWorkoutAssignment.getPosition())
                .build();
    }

    public ExerciseWithOrderInWorkoutDTO toDTO() {
        return ExerciseWithOrderInWorkoutDTO.builder()
                .exercise(exercise.toDTO())
                .position(position)
                .build();
    }
}
