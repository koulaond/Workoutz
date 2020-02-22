package com.ondrejkoula.endpoint.modelconverter;

import com.ondrejkoula.domain.TrainingPlanWorkout;
import com.ondrejkoula.dto.TrainingPlanWorkoutDto;
import com.ondrejkoula.dto.WorkoutDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TrainingPlanWorkoutModelToDtoConverter  extends AbstractConverter<TrainingPlanWorkout, TrainingPlanWorkoutDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected TrainingPlanWorkoutDto convert(TrainingPlanWorkout source) {
        TrainingPlanWorkoutDto target = new TrainingPlanWorkoutDto();
        target.setWeek(source.getWeek());
        target.setDayInWeek(source.getDayInWeek());
        target.setOrderWithinDay(source.getOrderWithinDay());
        target.setPhase(source.getPhase());
        WorkoutDto workoutDto = modelMapper.map(source.getWorkout(), WorkoutDto.class);
        target.setWorkout(workoutDto);
        return target;
    }
}
