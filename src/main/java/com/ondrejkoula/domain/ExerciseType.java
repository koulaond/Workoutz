package com.ondrejkoula.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExerciseType extends DomainEntity {

    private String type;

    private ExerciseCategory category;
}
