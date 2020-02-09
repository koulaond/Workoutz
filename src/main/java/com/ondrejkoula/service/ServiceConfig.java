package com.ondrejkoula.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public ExerciseService exerciseService() {
        return new ExerciseService();
    }

    @Bean
    public ExerciseTypeService exerciseTypeService() {
        return new ExerciseTypeService();
    }

    @Bean
    public MusclesService musclesService() {
        return new MusclesService();
    }

    @Bean
    public ExercisePresetService exercisePresetService() {
        return new ExercisePresetService();
    }

    @Bean
    public WorkoutExerciseUnitService workoutExerciseUnitService() {
        return new WorkoutExerciseUnitService();
    }

    @Bean
    public WorkoutService workoutService () {
        return new WorkoutService();
    }
}
