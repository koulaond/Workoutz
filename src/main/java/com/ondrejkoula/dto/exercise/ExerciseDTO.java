package com.ondrejkoula.dto.exercise;

import com.ondrejkoula.domain.exercise.Exercise;
import com.ondrejkoula.dto.AbstractDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class ExerciseDTO extends AbstractDTO {

    public ExerciseDTO(Long id, String status, String note) {
        super(id, status, note);
    }

    @Override
    public abstract Exercise toDomain();
}
