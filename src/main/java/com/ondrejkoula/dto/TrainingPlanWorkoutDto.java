package com.ondrejkoula.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingPlanWorkoutDto {

    private Long trainingPlanId;
    private Long workoutId;
    private Integer week;
    private Integer dayInWeek;
    private Integer orderWithinDay;
    private Integer phase;
    private WorkoutDto workout;
}
