package com.ondrejkoula.dto.exercise;

import com.ondrejkoula.dto.AbstractDTO;


public class ExerciseDTO extends AbstractDTO {

    public ExerciseDTO(Long id, String status, String note) {
        super(id, status, note);
    }
}