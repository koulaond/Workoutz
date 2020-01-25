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
}
