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

    private String value;

    private String category;

    @Builder
    public ExerciseTypeDTO(Long id, String status, String note, String value, String category) {
        super(id, status, note);
        this.value = value;
        this.category = category;
    }

    public static ExerciseTypeDTO from(@NotNull ExerciseType exerciseType) {
        return ExerciseTypeDTO.builder()
                .id(exerciseType.getId())
                .status(exerciseType.getStatus())
                .value(exerciseType.getValue())
                .note(exerciseType.getNote())
                .category(exerciseType.getCategory())
                .build();
    }

    @Override
    public ExerciseType toDomain() {
        return ExerciseType.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .value(getValue())
                .category(getCategory())
                .build();
    }
}
