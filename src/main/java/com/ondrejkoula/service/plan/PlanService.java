package com.ondrejkoula.service.plan;

import com.ondrejkoula.domain.plan.Plan;
import com.ondrejkoula.repository.plan.PlanRepository;
import com.ondrejkoula.service.GenericService;

public class PlanService extends GenericService<Plan> {

    public PlanService(PlanRepository repository) {
        super(repository);
    }
}
