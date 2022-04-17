package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.Exercise;
import com.ondrejkoula.service.exercise.ExerciseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseListEndpoint {

    private final ExerciseListService exerciseListService;

    @Autowired
    public ExerciseListEndpoint(ExerciseListService exerciseListService) {
        this.exerciseListService = exerciseListService;
    }

    @RequestMapping(value = "workout/{workoutId}")
    public List<Exercise> getExercisesForWorkout(@PathVariable("workoutId") Long workoutId) {
        return null;
    }
}
