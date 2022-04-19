package com.ondrejkoula.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.dto.AbstractDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseTypeDTO extends AbstractDTO {

    private String type;

    private String category;

    @Builder
    public ExerciseTypeDTO(Long id, String status, String note, String type, String category) {
        super(id, status, note);
        this.type = type;
        this.category = category;
    }

    public static ExerciseTypeDTO from(@NotNull ExerciseType exerciseType) {
        return ExerciseTypeDTO.builder()
                .id(exerciseType.getId())
                .status(exerciseType.getStatus())
                .type(exerciseType.getType())
                .category(exerciseType.getCategory())
                .build();
    }
}
