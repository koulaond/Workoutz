package com.ondrejkoula.service;

import com.ondrejkoula.domain.WorkoutExerciseUnit;
import com.ondrejkoula.repository.WorkoutExerciseUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WorkoutExerciseUnitService {

    @Autowired
    private WorkoutExerciseUnitRepository repository;

    public WorkoutExerciseUnit save(WorkoutExerciseUnit workoutExerciseUnit) {
        return repository.save(workoutExerciseUnit);
    }

    public List<WorkoutExerciseUnit> saveAll(List<WorkoutExerciseUnit> workoutExerciseUnits) {
        return repository.saveAll(workoutExerciseUnits);
    }

    public List<WorkoutExerciseUnit> findByWorkout(Long workoutId) {
        return repository.findByWorkoutIdOrderByPositionAsc(workoutId);
    }
}
