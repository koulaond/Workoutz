package com.ondrejkoula.endpoint.plan;

import com.ondrejkoula.domain.plan.Plan;
import com.ondrejkoula.dto.plan.PlanDTO;
import com.ondrejkoula.endpoint.CrudEndpoint;
import com.ondrejkoula.service.plan.PlanService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/plans")
public class PlanEndpoint extends CrudEndpoint<Plan, PlanDTO, PlanService> {

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
