package com.ondrejkoula.service.dependencies.exercise;

import com.ondrejkoula.domain.EntityType;
import com.ondrejkoula.domain.workout.ExerciseToWorkoutAssignment;
import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.dto.Dependencies;
import com.ondrejkoula.repository.jpa.workout.WorkoutExerciseRepository;
import com.ondrejkoula.service.dependencies.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExerciseDependencyService extends DependencyService {
    
    private final WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    public ExerciseDependencyService(WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    @Override
    public void doCollect(Long exerciseId, List<Dependencies> dependenciesList) {
        List<ExerciseToWorkoutAssignment> assignmentList = workoutExerciseRepository.getWorkoutsForExercise(exerciseId);
        List<Workout> workoutList = assignmentList.stream().map(assignment -> assignment.getPk().getWorkout()).collect(Collectors.toList());
        registerDependenciesForEntityType(EntityType.WORKOUT, workoutList, dependenciesList);
    }
}
