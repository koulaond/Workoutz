package com.ondrejkoula.endpoint.modelconverter;

import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.domain.preset.ExercisePreset;
import com.ondrejkoula.dto.WorkoutDto;
import com.ondrejkoula.dto.create.WorkoutExerciseUnitDto;
import com.ondrejkoula.dto.preset.ExercisePresetDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class WorkoutModelToDtoConverter extends AbstractConverter< Workout, WorkoutDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected WorkoutDto convert(Workout source) {
        WorkoutDto dto = new WorkoutDto();
        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setDescription(source.getDescription());
        dto.setNote(source.getNote());
        dto.setExerciseUnits(source.getExerciseUnits().stream()
                .map(createdUnit-> {
                    ExercisePreset exercisePreset = createdUnit.getExercisePreset();
                    ExercisePresetDto exercisePresetDto = modelMapper.map(exercisePreset, ExercisePresetDto.class);
                    WorkoutExerciseUnitDto unitDto = new WorkoutExerciseUnitDto();
                    unitDto.setPosition(createdUnit.getId().getPosition());
                    unitDto.setWorkoutId(createdUnit.getId().getWorkout().getId());
                    unitDto.setExercisePresetDto(exercisePresetDto);
                    return unitDto;
                })
                .sorted(comparing(WorkoutExerciseUnitDto::getPosition))
                .collect(Collectors.toList()));
        return dto;
    }
}
