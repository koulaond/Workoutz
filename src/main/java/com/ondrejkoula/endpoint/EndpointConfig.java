package com.ondrejkoula.endpoint;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ExerciseEndpoint exerciseEndpoint() {
        return new ExerciseEndpoint();
    }

    @Bean
    public ExerciseTypeEndpoint exerciseTypeEndpoint() {
        return new ExerciseTypeEndpoint();
    }

    @Bean
    public MusclesEndpoint musclesEndpoint() {
        return new MusclesEndpoint();
    }
}
