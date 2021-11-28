package com.ondrejkoula.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document
public class SetsAndRepetitions extends DomainEntity {

    private ExercisePrescription exercisePrescription;

    private ExerciseValue<Integer> seriesCount;

    private ExerciseValue<Integer> repetitionsCount;

    private ExerciseValue<Integer> weight;

    private Integer maxTimeSec;

    private Integer maxTimeMin;
}

