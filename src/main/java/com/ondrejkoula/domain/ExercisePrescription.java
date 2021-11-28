package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "exercise_prescription")
public class ExercisePrescription extends DomainEntity {

    private String label;

    @ManyToOne
    private ExerciseType exerciseType;

    private String description;
}
