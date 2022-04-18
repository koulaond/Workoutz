package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.ExerciseWithOrderInWorkout;
import com.ondrejkoula.dto.exercise.ExerciseDTOFactory;
import com.ondrejkoula.dto.exercise.ExercisesForWorkoutDTO;
import com.ondrejkoula.dto.exercise.WorkoutExerciseListItem;
import com.ondrejkoula.service.exercise.ExerciseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseListEndpoint {

    private final ExerciseListService exerciseListService;

    @Autowired
    public ExerciseListEndpoint(ExerciseListService exerciseListService) {
        this.exerciseListService = exerciseListService;
    }

    @RequestMapping(value = "workout/{workoutId}")
    public ExercisesForWorkoutDTO getExercisesForWorkout(@PathVariable("workoutId") Long workoutId) {
        List<ExerciseWithOrderInWorkout> exercisesForWorkout = exerciseListService.getExercisesForWorkout(workoutId);

        ExerciseDTOFactory factory = new ExerciseDTOFactory();

        return ExercisesForWorkoutDTO.builder()
                .workoutId(workoutId)
                .exercisesCount(exercisesForWorkout.size())
                .exercises(exercisesForWorkout.stream()
                        .map(exerciseWithOrderInWorkout ->
                                new WorkoutExerciseListItem(exerciseWithOrderInWorkout.getPosition(),
                                        factory.getDTOForExercise(exerciseWithOrderInWorkout.getExercise())))
                        .collect(toList()))
                .build();
    }
}
