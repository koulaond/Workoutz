package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class ExerciseType extends AbstractEntity {

    @Column(name = "exercise_type")
    private String type;
    private String additionalInfo;
}
