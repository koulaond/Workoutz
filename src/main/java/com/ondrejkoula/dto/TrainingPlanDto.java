package com.ondrejkoula.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingPlanDto {

    private Long id;
    private String name;
    private String description;
    private String note;
    private Integer weeks;
    private Integer daysPerWeek;
    private Integer phases;

}
