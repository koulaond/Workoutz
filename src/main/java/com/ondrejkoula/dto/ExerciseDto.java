package com.ondrejkoula.dto;

import lombok.Data;
import java.util.List;

@Data
public class ExerciseDto {
    private Long id;
    private String name;
    private String description;
    private List<String> muscles;
    private String type;
}
