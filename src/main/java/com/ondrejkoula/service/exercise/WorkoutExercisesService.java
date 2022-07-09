package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.Exercise;
import com.ondrejkoula.domain.exercise.ExerciseWithOrderInWorkout;
import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.domain.workout.WorkoutExercise;
import com.ondrejkoula.domain.workout.WorkoutExerciseId;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.exception.PositionOutOfRangeException;
import com.ondrejkoula.repository.exercise.NonSpecificExerciseRepository;
import com.ondrejkoula.repository.workout.WorkoutExerciseRepository;
import com.ondrejkoula.repository.workout.WorkoutRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WorkoutExercisesService {

    private final NonSpecificExerciseRepository exerciseRepository;

    private final WorkoutRepository workoutRepository;

    private final WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    public WorkoutExercisesService(NonSpecificExerciseRepository exerciseRepository,
                                   WorkoutRepository workoutRepository,
                                   WorkoutExerciseRepository workoutExerciseRepository) {

        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    public List<ExerciseWithOrderInWorkout> getExercisesForWorkout(Long workoutId) {
        return workoutExerciseRepository.getExercisesForWorkout(workoutId)
                .stream()
                .map(ExerciseWithOrderInWorkout::of)
                .collect(Collectors.toList());
    }

    public List<ExerciseWithOrderInWorkout> assignExerciseToWorkout(Long workoutId, Long exerciseId, Integer position) {
        Exercise exercise = getExerciseOrThrowException(exerciseId);
        Workout workout = getWorkoutOrThrowException(workoutId);

        validatePositionIsInRange(position, getExercisesForWorkout(workoutId));

        List<WorkoutExercise> allExercisesAfterPositionIncluding
                = workoutExerciseRepository.getExercisesForWorkoutAfterPositionIncluding(workoutId, position);

        moveSubsequentExercisesPositionsOneUp(allExercisesAfterPositionIncluding);
        assignNewExerciseToPositionInWorkout(exercise, workout, position);

        return getExercisesForWorkout(workoutId);
    }

    private void assignNewExerciseToPositionInWorkout(Exercise exercise, Workout workout, Integer position) {
        workoutExerciseRepository.save(WorkoutExercise.builder()
                .pk(WorkoutExerciseId.builder().exercise(exercise).workout(workout).build())
                .position(position)
                .build());
    }

    private void moveSubsequentExercisesPositionsOneUp(List<WorkoutExercise> allExercisesAfterPositionIncluding) {
        allExercisesAfterPositionIncluding.forEach(following -> {
            following.setPosition(following.getPosition() + 1);
            workoutExerciseRepository.save(following);
        });
    }

    private void validatePositionIsInRange(Integer position, List<ExerciseWithOrderInWorkout> exercisesForWorkout) {
        if (exercisesForWorkout.size() < position || position < 0) {
            throw new PositionOutOfRangeException(exercisesForWorkout.size(), position, "workout", "exercise");
        }
    }

    private Workout getWorkoutOrThrowException(Long workoutId) {
        return workoutRepository.findById(workoutId)
                .orElseThrow(() -> new DataNotFoundException("Workout not found", "notFound"));
    }

    private Exercise getExerciseOrThrowException(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new DataNotFoundException("Exercise not found", "notFound"));
    }

}
