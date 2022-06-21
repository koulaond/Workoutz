package com.ondrejkoula.service.workout;

import com.ondrejkoula.domain.exercise.Exercise;
import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.domain.workout.WorkoutExercise;
import com.ondrejkoula.domain.workout.WorkoutExerciseId;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.repository.ExerciseRepository;
import com.ondrejkoula.repository.workout.WorkoutExerciseRepository;
import com.ondrejkoula.repository.workout.WorkoutRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WorkoutService extends GenericService<Workout> {

    private final ExerciseRepository exerciseRepository;

    private final WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    public WorkoutService(WorkoutRepository repository,
                          ExerciseRepository exerciseRepository,
                          WorkoutExerciseRepository workoutExerciseRepository) {
        super(repository);
        this.exerciseRepository = exerciseRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    public List<Workout> getWorkoutsForExercise(Long exerciseId) {
        return getForExercise(exerciseId).stream()
                .map(workoutExercise -> workoutExercise.getPk().getWorkout())
                .collect(Collectors.toList());
    }

    public void assignExerciseToWorkout(Long workoutId, Long exerciseId, Integer position) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new DataNotFoundException("Exercise not found", "notFound"));

        Workout workout = repository.findById(workoutId)
                .orElseThrow(() -> new DataNotFoundException("Workout not found", "notFound"));

        List<WorkoutExercise> workoutExercises = workoutExerciseRepository.getExercisesForWorkoutAfterPosition(workoutId, position);

        workoutExercises.forEach(following -> {
            following.setPosition(following.getPosition() + 1);
            workoutExerciseRepository.save(following);
        });

        workoutExerciseRepository.save(WorkoutExercise.builder()
                .pk(WorkoutExerciseId.builder().exercise(exercise).workout(workout).build())
                .position(position)
                .build());
    }

    private List<WorkoutExercise> getForExercise(Long exerciseId) {
        return workoutExerciseRepository.getWorkoutsForExercise(exerciseId);
    }

}
