package com.ondrejkoula.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.dto.AbstractDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
