package com.ondrejkoula.repository.plan;

import com.ondrejkoula.PersistenceTest;
import com.ondrejkoula.domain.plan.Plan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

class PlanRepositoryTest extends PersistenceTest {

    @Autowired
    PlanRepository planRepository;

    @Test
    void shouldSuccessfullyPersistPlanRecord() {
        LocalDateTime expectedPlanStart = LocalDateTime.of(2022, 7, 1, 18, 0, 0, 0);
        LocalDateTime expectedPlanEnd = LocalDateTime.of(2022, 7, 4, 18, 0, 0, 0);

        Plan toSave = Plan.builder().title("Plan to save").expectedPlanStart(expectedPlanStart)
                .expectedPlanEnd(expectedPlanEnd).build();

        Plan saved = planRepository.save(toSave);
        System.out.println(saved);
    }
}