package com.ondrejkoula.service.plan;

import com.ondrejkoula.domain.plan.Plan;
import com.ondrejkoula.repository.plan.PlanRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class PlanService extends GenericService<Plan> {

    public PlanService(PlanRepository repository) {
        super(repository);
    }
}
