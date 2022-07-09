package com.ondrejkoula.endpoint.plan;

import com.ondrejkoula.domain.plan.Plan;
import com.ondrejkoula.dto.plan.PlanDTO;
import com.ondrejkoula.endpoint.UpdateEndpoint;
import com.ondrejkoula.service.plan.PlanService;


public class PlanEndpoint extends UpdateEndpoint<Plan, PlanDTO, PlanService> {

    public PlanEndpoint(PlanService service) {
        super(service);
    }

    @Override
    protected Plan toDomain(PlanDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected PlanDTO toDTO(Plan domainEntity) {
        return domainEntity.toDTO();
    }
}
