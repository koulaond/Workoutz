package com.ondrejkoula.endpoint.modelconverter;

import com.ondrejkoula.domain.Exercise;
import com.ondrejkoula.domain.preset.StandardSetPreset;
import com.ondrejkoula.dto.ExerciseDto;
import com.ondrejkoula.dto.preset.StandardSetPresetDto;
import org.modelmapper.AbstractConverter;

public class StandardSetPresetModelToDtoConverter extends AbstractConverter<StandardSetPreset, StandardSetPresetDto> {

    @Override
    protected StandardSetPresetDto convert(StandardSetPreset source) {
        StandardSetPresetDto target = new StandardSetPresetDto();
        target.setId(source.getId());
        target.setSeries(source.getSeries());
        target.setRepsPerSeries(source.getRepsPerSeries());
        target.setLabel(source.getLabel());
        target.setDescription(source.getDescription());
        Exercise exercise = source.getExercise();
        target.setExerciseDto(ExerciseDto.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .description(exercise.getDescription())
                .type(exercise.getExerciseType().getType())
                .build());
        return target;
    }
}
