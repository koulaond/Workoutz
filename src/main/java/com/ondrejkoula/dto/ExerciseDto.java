package com.ondrejkoula.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ExerciseDto {
    private Long id;
    private String name;
    private String description;
    private List<String> muscles;
    private String type;
}
