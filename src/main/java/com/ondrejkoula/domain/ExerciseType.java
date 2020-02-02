package com.ondrejkoula.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class ExerciseType extends AbstractEntity {

    @Column(name = "exercise_type")
    private String type;
    private String additionalInfo;
}
