package com.ondrejkoula.dto.exercise;

import com.ondrejkoula.dto.AbstractDTO;
import lombok.Data;

@Data
public abstract class ExerciseDTO extends AbstractDTO {

    public ExerciseDTO(Long id, String status, String note) {
        super(id, status, note);
    }
}
