package com.ondrejkoula.repository.plan;

import com.ondrejkoula.domain.plan.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
