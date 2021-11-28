package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "exercise_type")
public class ExerciseType extends DomainEntity {

    private String type;

    private ExerciseCategory category;
}
