package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class ExerciseValue<T> {

    private T value;

    private Type valueType;

    private enum Type {
        PRESET, GOAL
    }
}
