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
        List<WorkoutExerciseUnit> units = source.getExerciseUnits()
                .stream()
                .map(unitDto -> {
                    ExercisePreset exercisePreset = exercisePresetService.findById(unitDto.getExercisePresetId());
                    return WorkoutExerciseUnit.builder()
                            .exercisePreset(exercisePreset)
                            .id(WorkoutExerciseUnit.WorkoutExerciseUnitId.builder()
                                    .position(unitDto.getPosition())
                                    .workout(workout)
                                    .build())
                            .build();
                })
                .collect(Collectors.toList());
        workout.setExerciseUnits(units);
        return workout;
    }
}
