package com.ondrejkoula.endpoint.modelconverter;

import com.ondrejkoula.domain.Exercise;
import com.ondrejkoula.domain.preset.StandardSetPreset;
import com.ondrejkoula.dto.create.StandardSetPresetCreateDto;
import com.ondrejkoula.service.ExerciseService;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;

public class StandardSetPresetDtoToModelConverter extends AbstractConverter<StandardSetPresetCreateDto, StandardSetPreset> {

    @Autowired
    private ExerciseService exerciseService;

    @Override
    protected StandardSetPreset convert(StandardSetPresetCreateDto source) {
        StandardSetPreset target = new StandardSetPreset();
        Long exerciseId = source.getExerciseId();
        Exercise exercise = exerciseService.findById(exerciseId);
        if (exercise == null) throw new IllegalStateException("Exercise not found"); // TODO replace by app-specific exceptions
        target.setId(source.getId());
        target.setExercise(exercise);
        target.setSeries(source.getSeries());
        target.setRepsPerSeries(source.getRepsPerSeries());
        target.setLabel(source.getLabel());
        target.setDescription(source.getDescription());
        return target;
    }
}
