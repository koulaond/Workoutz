package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.preset.ExercisePreset;
import com.ondrejkoula.domain.preset.StandardSetPreset;
import com.ondrejkoula.domain.preset.SuperSetPreset;
import com.ondrejkoula.dto.preset.ExercisePresetDto;
import com.ondrejkoula.dto.preset.StandardSetPresetDto;
import com.ondrejkoula.dto.preset.SuperSetPresetDto;
import com.ondrejkoula.dto.preset.create.StandardSetPresetCreateDto;
import com.ondrejkoula.dto.preset.create.SuperSetPresetCreateDto;
import com.ondrejkoula.endpoint.modelconverter.StandardSetPresetDtoToModelConverter;
import com.ondrejkoula.endpoint.modelconverter.StandardSetPresetModelToDtoConverter;
import com.ondrejkoula.endpoint.modelconverter.SuperSetPresetDtoToModelConverter;
import com.ondrejkoula.endpoint.modelconverter.SuperSetPresetModelToDtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(StandardSetPresetCreateDto.class, StandardSetPreset.class).setConverter(exercisePresetDtoToModelConverter()).include(ExercisePreset.class);
        modelMapper.createTypeMap(StandardSetPreset.class, StandardSetPresetDto.class).setConverter(standardSetPresetModelToDtoConverter()).include(ExercisePresetDto.class);
        modelMapper.createTypeMap(SuperSetPresetCreateDto.class, SuperSetPreset.class).setConverter(superSetPresetDtoToModelConverter()).include(ExercisePreset.class);
        modelMapper.createTypeMap(SuperSetPreset.class, SuperSetPresetDto.class).setConverter(superSetPresetModelToDtoConverter()).include(ExercisePresetDto.class);
        return modelMapper;
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
}
