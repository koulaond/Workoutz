package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.Condition;
import com.ondrejkoula.dto.exercise.ConditionDTO;
import com.ondrejkoula.endpoint.UpdateEndpoint;
import com.ondrejkoula.service.exercise.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/condition")
public class ConditionEndpoint extends UpdateEndpoint<Condition, ConditionDTO, ConditionService> {

    @Autowired
    public ConditionEndpoint(ConditionService service) {
        super(service);
    }

    @Override
    protected Condition toDomain(ConditionDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected ConditionDTO toDTO(Condition domainEntity) {
        return domainEntity.toDTO();
    }
}
