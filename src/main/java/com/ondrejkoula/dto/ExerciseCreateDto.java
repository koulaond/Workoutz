package com.ondrejkoula.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ExerciseCreateDto {
    private String name;
    private String description;
    private Set<Long> muscleIds;
    private Long typeId;
}
