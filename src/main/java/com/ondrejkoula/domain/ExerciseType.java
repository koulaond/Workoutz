package com.ondrejkoula.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class ExerciseType extends AbstractEntity {

    private String type;
    private String additionalInfo;
}
