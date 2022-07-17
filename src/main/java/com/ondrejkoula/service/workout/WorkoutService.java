package com.ondrejkoula.service.workout;

import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.domain.workout.ExerciseToWorkoutAssignment;
import com.ondrejkoula.repository.jpa.workout.WorkoutExerciseRepository;
import com.ondrejkoula.repository.jpa.workout.WorkoutRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WorkoutService extends GenericService<Workout> {

    private final WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    public WorkoutService(WorkoutRepository repository,
                          WorkoutExerciseRepository workoutExerciseRepository) {
        super(repository);
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    public List<Workout> getWorkoutsForExercise(Long exerciseId) {
        List<ExerciseToWorkoutAssignment> workoutsForExercise = workoutExerciseRepository.getWorkoutsForExercise(exerciseId);

        return workoutsForExercise.stream()
                .map(workoutExercise -> workoutExercise.getPk().getWorkout())
                .collect(Collectors.toList());
    }


}
