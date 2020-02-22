package com.ondrejkoula.dto.create;

import lombok.Data;

import java.util.Set;

@Data
public class ExerciseCreateDto {
    private String name;
    private String description;
    private Set<Long> muscleIds;
    private Long typeId;
}
