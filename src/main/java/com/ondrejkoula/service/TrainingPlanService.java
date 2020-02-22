package com.ondrejkoula.service;

import com.ondrejkoula.domain.TrainingPlan;
import com.ondrejkoula.domain.TrainingPlanWorkout;
import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.repository.TrainingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TrainingPlanService {

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private TrainingPlanWorkoutService trainingPlanWorkoutService;

    public TrainingPlan addWorkoutToTrainingPlan(Long trainingPlanId, Long workoutId, Integer week, Integer day, Integer orderWithinDay, Integer phase) {
        TrainingPlan trainingPlan = trainingPlanRepository.findById(trainingPlanId).orElseThrow(IllegalStateException::new);
        if (week < 1 || week > trainingPlan.getWeeks()) throw new IllegalStateException(); // TODO replace ex
        if (day < 1 || day > trainingPlan.getDaysPerWeek()) throw new IllegalStateException();
        if (phase < 1 || phase > trainingPlan.getPhases()) throw new IllegalStateException();

        Workout workout = workoutService.findById(workoutId);
        TrainingPlanWorkout trainingPlanWorkout = trainingPlanWorkout(trainingPlan, workout, week, day, orderWithinDay, phase);
        trainingPlanWorkoutService.save(trainingPlanWorkout);
        return trainingPlan;
    }

    private TrainingPlanWorkout trainingPlanWorkout(TrainingPlan trainingPlan, Workout workout, Integer week, Integer day, Integer orderWithinDay, Integer phase) {
        TrainingPlanWorkout trainingPlanWorkout = new TrainingPlanWorkout();
        trainingPlanWorkout.setTrainingPlan(trainingPlan);
        trainingPlanWorkout.setWorkout(workout);
        trainingPlanWorkout.setWeek(week);
        trainingPlanWorkout.setDayInWeek(day);
        trainingPlanWorkout.setPhase(phase);
        trainingPlanWorkout.setOrderWithinDay(orderWithinDay);
        return trainingPlanWorkout;
    }

    public TrainingPlan save(TrainingPlan trainingPlan) {
        return trainingPlanRepository.save(trainingPlan);
    }

    public TrainingPlan findById(Long id) {
        return trainingPlanRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public List<TrainingPlan> findAll() {
        return trainingPlanRepository.findAll();
    }

    public void delete(Long id) {
        trainingPlanRepository.deleteById(id);
    }
}
