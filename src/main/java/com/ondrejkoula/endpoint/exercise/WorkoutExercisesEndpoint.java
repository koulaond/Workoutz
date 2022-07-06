package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.ExerciseWithOrderInWorkout;
import com.ondrejkoula.dto.exercise.ExercisesForWorkoutDTO;
import com.ondrejkoula.dto.exercise.WorkoutExerciseListItem;
import com.ondrejkoula.dto.workout.AssignExerciseToWorkoutDTO;
import com.ondrejkoula.dto.workout.WorkoutDTO;
import com.ondrejkoula.service.exercise.WorkoutExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/exercises")
public class WorkoutExercisesEndpoint {

    private final WorkoutExercisesService workoutExercisesService;


    @Autowired
    public WorkoutExercisesEndpoint(WorkoutExercisesService workoutExercisesService) {
        this.workoutExercisesService = workoutExercisesService;
    }

    @RequestMapping(value = "workout/{workoutId}")
    public ExercisesForWorkoutDTO getExercisesForWorkout(@PathVariable("workoutId") Long workoutId) {
        List<ExerciseWithOrderInWorkout> exercisesForWorkout = workoutExercisesService.getExercisesForWorkout(workoutId);

        return ExercisesForWorkoutDTO.builder()
                .workoutId(workoutId)
                .exercisesCount(exercisesForWorkout.size())
                .exercises(exercisesForWorkout.stream()
                        .map(exerciseWithOrderInWorkout ->
                                new WorkoutExerciseListItem(exerciseWithOrderInWorkout.getPosition(),
                                        exerciseWithOrderInWorkout.getExercise().toDTO()))
                        .collect(toList()))
                .build();
    }

    @RequestMapping(value = "assign-to-workout")
    public WorkoutDTO assignExerciseToWorkout(@RequestBody AssignExerciseToWorkoutDTO dto) {
        List<ExerciseWithOrderInWorkout> exercisesForWorkout = workoutExercisesService.assignExerciseToWorkout(dto.getWorkoutId(), dto.getExerciseId(), dto.getPosition());


        return null;
    }
}
