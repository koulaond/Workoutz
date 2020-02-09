package com.ondrejkoula.endpoint.modelconverter;

import com.ondrejkoula.domain.Exercise;
import com.ondrejkoula.domain.preset.SuperSetPreset;
import com.ondrejkoula.dto.create.SuperSetPresetCreateDto;
import com.ondrejkoula.service.ExerciseService;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SuperSetPresetDtoToModelConverter extends AbstractConverter<SuperSetPresetCreateDto, SuperSetPreset> {

    @Autowired
    private ExerciseService exerciseService;

    @Override
    protected SuperSetPreset convert(SuperSetPresetCreateDto source) {
        SuperSetPreset target = new SuperSetPreset();
        List<Long> exerciseIds = source.getExerciseIds();
        List<Exercise> exercises = new ArrayList<>();
        for (Long exerciseId : exerciseIds) {
            Exercise exercise = exerciseService.findById(exerciseId);
            if (exercise == null) throw new IllegalStateException("Exercise not found"); // TODO replace by app-specific exceptions
            exercises.add(exercise);
        }
        target.setId(source.getId());
        target.setExercises(exercises);
        target.setSeries(source.getSeries());
        target.setRepsPerSeries(source.getRepsPerSeries());
        target.setLabel(source.getLabel());
        target.setDescription(source.getDescription());
        return target;
    }
}
