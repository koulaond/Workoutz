package com.ondrejkoula.endpoint.modelconverter;

import com.ondrejkoula.domain.preset.SuperSetPreset;
import com.ondrejkoula.dto.ExerciseDto;
import com.ondrejkoula.dto.preset.SuperSetPresetDto;
import org.modelmapper.AbstractConverter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SuperSetPresetModelToDtoConverter extends AbstractConverter<SuperSetPreset, SuperSetPresetDto> {

    @Override
    protected SuperSetPresetDto convert(SuperSetPreset source) {
        SuperSetPresetDto target = new SuperSetPresetDto();
        target.setId(source.getId());
        target.setSeries(source.getSeries());
        target.setRepsPerSeries(source.getRepsPerSeries());
        target.setLabel(source.getLabel());
        target.setDescription(source.getDescription());
        List<ExerciseDto> exercises = source.getExercises().stream()
                .map(exercise ->
                        ExerciseDto.builder()
                                .id(exercise.getId())
                                .name(exercise.getName())
                                .description(exercise.getDescription())
                                .type(exercise.getExerciseType().getType())
                                .build()
                ).collect(toList());
        target.setExerciseDtos(exercises);
        return target;
    }
}
