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

public class WorkoutCreateDtoToModelConverter<T  extends WorkoutCreateDto> extends AbstractConverter<T, Workout> {

    @Autowired
    private ExercisePresetService exercisePresetService;

    @Override
    protected Workout convert(T source) {
        Workout target = new Workout();
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setNote(source.getNote());
        target.setName(source.getName());
        List<WorkoutExerciseUnit> units = source.getExerciseUnits()
                .stream()
                .map(unitDto -> {
                    ExercisePreset exercisePreset = exercisePresetService.findById(unitDto.getExercisePresetId());
                    return WorkoutExerciseUnit.builder()
                            .exercisePreset(exercisePreset)
                            .position(unitDto.getPosition())
                            .workout(target)
                            .build();
                })
                .collect(Collectors.toList());
        target.setExerciseUnits(units);
        return target;
    }
}
