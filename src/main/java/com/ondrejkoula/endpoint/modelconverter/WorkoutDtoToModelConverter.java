package com.ondrejkoula.endpoint.modelconverter;

import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.domain.WorkoutExerciseUnit;
import com.ondrejkoula.domain.preset.ExercisePreset;
import com.ondrejkoula.dto.create.WorkoutCreateDto;
import com.ondrejkoula.service.ExercisePresetService;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class WorkoutDtoToModelConverter extends AbstractConverter<WorkoutCreateDto, Workout> {

    @Autowired
    private ExercisePresetService exercisePresetService;

    @Override
    protected Workout convert(WorkoutCreateDto source) {
        Workout workout = new Workout();
        workout.setName(source.getName());
        workout.setDescription(source.getDescription());
        workout.setNote(source.getNote());
        workout.setName(source.getName());
        workout.setId(source.getId());
        List<WorkoutExerciseUnit> units = source.getExerciseUnits()
                .stream()
                .map(unitDto -> {
                    ExercisePreset exercisePreset = exercisePresetService.findById(unitDto.getExercisePresetId());
                    return WorkoutExerciseUnit.builder()
                            .exercisePreset(exercisePreset)
                            .position(unitDto.getPosition())
                            .workout(workout)
                            .build();
                })
                .collect(Collectors.toList());
        workout.setExerciseUnits(units);
        return workout;
    }
}
