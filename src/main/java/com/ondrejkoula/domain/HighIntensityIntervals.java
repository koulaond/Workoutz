package com.ondrejkoula.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class HighIntensityIntervals extends DomainEntity{

    private ExercisePrescription exercisePrescription;

    private ExerciseValue<Integer> intervalsCount;

    private Integer intensityIntervalTime;

    private Integer calmIntervalTime;
}
