package com.ondrejkoula.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class ExerciseValue<T> {

    private T value;

    private Type valueType;

    private enum Type {
        PRESET, GOAL
    }
}
