package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.TrainingPlan;
import com.ondrejkoula.domain.TrainingPlanWorkout;
import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.domain.preset.ExercisePreset;
import com.ondrejkoula.domain.preset.StandardSetPreset;
import com.ondrejkoula.domain.preset.SuperSetPreset;
import com.ondrejkoula.dto.TrainingPlanDto;
import com.ondrejkoula.dto.TrainingPlanWorkoutDto;
import com.ondrejkoula.dto.WorkoutDto;
import com.ondrejkoula.dto.create.StandardSetPresetCreateDto;
import com.ondrejkoula.dto.create.SuperSetPresetCreateDto;
import com.ondrejkoula.dto.create.WorkoutCreateDto;
import com.ondrejkoula.dto.create.WorkoutUpdateDto;
import com.ondrejkoula.dto.preset.ExercisePresetDto;
import com.ondrejkoula.dto.preset.StandardSetPresetDto;
import com.ondrejkoula.dto.preset.SuperSetPresetDto;
import com.ondrejkoula.endpoint.modelconverter.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class EndpointConfig {

    @PostConstruct
    public void postConstruct() {
        ModelMapper modelMapper = modelMapper();
        modelMapper.createTypeMap(StandardSetPresetCreateDto.class, StandardSetPreset.class).setConverter(exercisePresetDtoToModelConverter()).include(ExercisePreset.class);
        modelMapper.createTypeMap(StandardSetPreset.class, StandardSetPresetDto.class).setConverter(standardSetPresetModelToDtoConverter()).include(ExercisePresetDto.class);
        modelMapper.createTypeMap(SuperSetPresetCreateDto.class, SuperSetPreset.class).setConverter(superSetPresetDtoToModelConverter()).include(ExercisePreset.class);
        modelMapper.createTypeMap(SuperSetPreset.class, SuperSetPresetDto.class).setConverter(superSetPresetModelToDtoConverter()).include(ExercisePresetDto.class);
        modelMapper.createTypeMap(Workout.class, WorkoutDto.class).setConverter(workoutModelToDtoConverter());
        modelMapper.createTypeMap(WorkoutCreateDto.class, Workout.class).setConverter(workoutCreateDtoToModelConverter());
        modelMapper.createTypeMap(WorkoutUpdateDto.class, Workout.class).setConverter(workoutUpdateDtoToModelConverter());
        modelMapper.createTypeMap(TrainingPlan.class, TrainingPlanDto.class).setConverter(trainingPlanModelToDtoConverter());
        modelMapper.createTypeMap(TrainingPlanDto.class, TrainingPlan.class).setConverter(trainingPlanDtoToModelConverter());
        modelMapper.createTypeMap(TrainingPlanWorkout.class, TrainingPlanWorkoutDto.class).setConverter(trainingPlanWorkoutModelToDtoConverter());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public StandardSetPresetModelToDtoConverter standardSetPresetModelToDtoConverter() {
        return new StandardSetPresetModelToDtoConverter();
    }

    @Bean
    public StandardSetPresetDtoToModelConverter exercisePresetDtoToModelConverter() {
        return new StandardSetPresetDtoToModelConverter();
    }

    @Bean
    public SuperSetPresetModelToDtoConverter superSetPresetModelToDtoConverter() {
        return new SuperSetPresetModelToDtoConverter();
    }

    @Bean
    public SuperSetPresetDtoToModelConverter superSetPresetDtoToModelConverter() {
        return new SuperSetPresetDtoToModelConverter();
    }

    @Bean
    public WorkoutCreateDtoToModelConverter<WorkoutCreateDto> workoutCreateDtoToModelConverter() {
        return new WorkoutCreateDtoToModelConverter<>();
    }

    @Bean
    public  WorkoutUpdateDtoToModelConverter workoutUpdateDtoToModelConverter() {
        return new WorkoutUpdateDtoToModelConverter();
    }

    @Bean
    public WorkoutModelToDtoConverter workoutModelToDtoConverter() {
        return new WorkoutModelToDtoConverter();
    }

    @Bean
    public TrainingPlanDtoToModelConverter trainingPlanDtoToModelConverter() {
        return new TrainingPlanDtoToModelConverter();
    }

    @Bean
    public TrainingPlanModelToDtoConverter trainingPlanModelToDtoConverter() {
        return new TrainingPlanModelToDtoConverter();
    }

    @Bean
    public TrainingPlanWorkoutModelToDtoConverter trainingPlanWorkoutModelToDtoConverter() {
        return new TrainingPlanWorkoutModelToDtoConverter();
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

    @Bean
    public ExercisePresetEndpoint exercisePresetEndpoint() {
        return new ExercisePresetEndpoint();
    }

    @Bean
    public WorkoutEndpoint workoutExerciseUnitEndpoint() {
        return new WorkoutEndpoint();
    }

    @Bean
    public TrainingPlanEndpoint  trainingPlanEndpoint() {
        return new TrainingPlanEndpoint();
    }
}
