package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "exercise_type")
public class ExerciseType extends DomainEntity {

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;
}
