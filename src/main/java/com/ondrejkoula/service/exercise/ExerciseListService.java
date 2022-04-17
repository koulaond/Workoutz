package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.ExerciseWithOrderInWorkout;
import com.ondrejkoula.repository.workout.WorkoutExerciseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ExerciseListService {

    private final WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    public ExerciseListService(WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    public List<ExerciseWithOrderInWorkout> getExercisesForWorkout(Long workoutId) {
        return workoutExerciseRepository.getExercisesForWorkout(workoutId)
                .stream()
                .map(ExerciseWithOrderInWorkout::of)
                .collect(Collectors.toList());
    }
}
