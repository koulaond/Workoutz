package com.ondrejkoula.service;


import com.ondrejkoula.repository.ExerciseTypeRepository;
import com.ondrejkoula.repository.SetsAndRepetitionsRepository;
import org.springframework.context.annotation.Bean;


public class ServiceConfig {

    @Bean
    public ExerciseTypeService exerciseTypeService(ExerciseTypeRepository repository) {
        return new ExerciseTypeService(repository);
    }

    @Bean
    public SetsAndRepetitionsService setsAndRepetitionsService(SetsAndRepetitionsRepository repository) {
        return new SetsAndRepetitionsService(repository);
    }

}

