package com.ondrejkoula.service;

import com.ondrejkoula.domain.TrainingPlanWorkout;
import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.domain.WorkoutExerciseUnit;
import com.ondrejkoula.repository.TrainingPlanWorkoutRepository;
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

    @Autowired
    private TrainingPlanWorkoutRepository trainingPlanWorkoutRepository;

    @Transactional
    public Workout create(Workout workout) {
        return workoutRepository.save(workout);
    }

    @Transactional
    public Workout update(Workout workout) {
        if (workout.getId() != null) {
            deleteChildrenUnits(workout.getId());
        }
        return workoutRepository.save(workout);
    }

    public List<TrainingPlanWorkout> findWorkoutsForTrainingPlan(Long trainingPlanId) {
        return trainingPlanWorkoutRepository.findByTrainingPlanId(trainingPlanId);
    }

    private void deleteChildrenUnits(Long workoutId) {
        // Naive solution - delete all previous units and update workout with new ones
        List<WorkoutExerciseUnit> found = workoutExerciseUnitRepository.findByWorkoutIdOrderByPositionAsc(workoutId);
        found.forEach(workoutExerciseUnit -> workoutExerciseUnitRepository.delete(workoutExerciseUnit));
    }

    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    public Workout findById(Long id) {
        return workoutRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public void delete(Long id) {
        deleteChildrenUnits(id);
        workoutRepository.deleteById(id);
    }
}
