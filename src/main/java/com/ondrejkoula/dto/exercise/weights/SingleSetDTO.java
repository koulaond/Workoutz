package com.ondrejkoula.dto.exercise.weights;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.weights.SingleSet;
import com.ondrejkoula.dto.ConvertibleFromDTOToDomain;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleSetDTO implements ConvertibleFromDTOToDomain {

    private Integer position;
    private Integer repetitions;

    private Integer repetitionsGoal;

    private Integer weight;

    private Integer weightGoal;

    @Builder
    public SingleSetDTO(Integer position, Integer repetitions, Integer repetitionsGoal, Integer weight, Integer weightGoal) {
        this.position = position;
        this.repetitions = repetitions;
        this.repetitionsGoal = repetitionsGoal;
        this.weight = weight;
        this.weightGoal = weightGoal;
    }


    @Override
    public SingleSet toDomain() {
        return SingleSet.builder()
                .position(getPosition())
                .repetitions(getRepetitions())
                .repetitionsGoal(getRepetitionsGoal())
                .weight(getWeight())
                .weightGoal(getWeightGoal())
                .build();
    }
}
