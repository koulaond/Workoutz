package com.ondrejkoula.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.ExerciseType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseTypeDTO {

    private long id;

    private String status;

    private String type;

    private String category;

    public static ExerciseTypeDTO from(@NotNull ExerciseType exerciseType) {
        return ExerciseTypeDTO.builder()
                .id(exerciseType.getId())
                .status(exerciseType.getStatus())
                .type(exerciseType.getType())
                .category(exerciseType.getCategory())
                .build();
    }
}
