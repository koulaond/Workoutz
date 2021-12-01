package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "exercise_prescription")
public class ExercisePrescription extends DomainEntity {

    @Column(name = "label")
    private String label;

    @ManyToOne
    @JoinColumn(name = "exercise_type_id")
    private ExerciseType exerciseType;

    @Column(name = "description")
    private String description;
}
