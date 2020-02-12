package com.ondrejkoula.service;

import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.domain.WorkoutExerciseUnit;
import com.ondrejkoula.repository.WorkoutExerciseUnitRepository;
import com.ondrejkoula.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutExerciseUnitRepository workoutExerciseUnitRepository;

    @Transactional
    public Workout create(Workout workout) {
        if (workout.getId() != null) {
            // Naive solution - delete all previous units and update workout with new ones
            List<WorkoutExerciseUnit> found = workoutExerciseUnitRepository.findByWorkoutIdOrderByPositionAsc(workout.getId());
            found.forEach(workoutExerciseUnit -> workoutExerciseUnitRepository.delete(workoutExerciseUnit));
        }
        return workoutRepository.save(workout);
    }

    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    public Workout findById(Long id) {
        return workoutRepository.findById(id).orElseThrow(IllegalStateException::new);
    }
}
