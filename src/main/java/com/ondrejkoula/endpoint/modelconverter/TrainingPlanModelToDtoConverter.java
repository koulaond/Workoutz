package com.ondrejkoula.endpoint.modelconverter;

import com.ondrejkoula.domain.TrainingPlan;
import com.ondrejkoula.dto.TrainingPlanDto;
import org.modelmapper.AbstractConverter;

public class TrainingPlanModelToDtoConverter extends AbstractConverter<TrainingPlan, TrainingPlanDto> {

    @Override
    protected TrainingPlanDto convert(TrainingPlan source) {
        TrainingPlanDto target = new TrainingPlanDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setNote(source.getNote());
        target.setWeeks(source.getWeeks());
        target.setDaysPerWeek(source.getDaysPerWeek());
        target.setPhases(source.getPhases());
        return target;
    }
}
