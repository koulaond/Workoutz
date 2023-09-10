package com.ondrejkoula.domain.exercise.weights;

import com.ondrejkoula.domain.ConvertibleFromDomainToDTO;
import com.ondrejkoula.dto.exercise.weights.SingleSetDTO;
import com.ondrejkoula.service.validation.annotation.Required;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class WeightSingleSet implements ConvertibleFromDomainToDTO {

    @Required
    private Integer position;

    @Required
    private Integer repetitions;

    private Integer repetitionsGoal;

    @Required
    private Integer weight;

    private Integer weightGoal;

    @Builder
    public WeightSingleSet(Integer position, Integer repetitions, Integer repetitionsGoal, Integer weight, Integer weightGoal) {
        this.position = position;
        this.repetitions = repetitions;
        this.repetitionsGoal = repetitionsGoal;
        this.weight = weight;
        this.weightGoal = weightGoal;
    }

    @Override
    public SingleSetDTO toDTO() {
        return SingleSetDTO.builder()
                .position(getPosition())
                .repetitions(getRepetitions())
                .repetitionsGoal(getRepetitionsGoal())
                .weight(getWeight())
                .weightGoal(getWeightGoal())
                .build();
    }
}
