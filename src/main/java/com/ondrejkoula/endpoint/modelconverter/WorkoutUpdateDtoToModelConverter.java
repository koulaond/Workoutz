package com.ondrejkoula.endpoint.modelconverter;

import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.dto.create.WorkoutUpdateDto;

public class WorkoutUpdateDtoToModelConverter extends WorkoutCreateDtoToModelConverter<WorkoutUpdateDto> {

    @Override
    protected Workout convert(WorkoutUpdateDto source) {
        Workout target = super.convert(source);
        target.setId(source.getId());
        return target;
    }
}
