package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.weights.SingleSet;
import com.ondrejkoula.domain.exercise.weights.Weights;
import com.ondrejkoula.dto.exercise.weights.SingleSetDTO;
import com.ondrejkoula.dto.exercise.weights.WeightsDTO;
import com.ondrejkoula.endpoint.CrudEndpoint;
import com.ondrejkoula.service.exercise.WeightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/exercises/weights")
public class WeightsEndpoint extends CrudEndpoint<Weights, WeightsDTO, WeightsService> {

    @Autowired
    public WeightsEndpoint(WeightsService service) {
        super(service);
    }

    @PutMapping(value = "/{id}/update-sets", produces = "application/json")
    public WeightsDTO updateSetsInWeightsExercise(@PathVariable Long id, @RequestBody List<SingleSetDTO> setDtos) {

        List<SingleSet> sets = setDtos.stream().map(SingleSetDTO::toDomain).collect(Collectors.toList());

        Weights updated = service.updateSetsInWeightsExercise(id, sets);
        return updated.toDTO();
    }

    @Override
    protected Weights toDomain(WeightsDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected WeightsDTO toDTO(Weights domainEntity) {
        return domainEntity.toDTO();
    }
}
