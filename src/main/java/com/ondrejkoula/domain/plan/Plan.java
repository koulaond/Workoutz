package com.ondrejkoula.domain.plan;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.plan.PlanDTO;
import com.ondrejkoula.service.validation.annotation.Before;
import com.ondrejkoula.service.validation.annotation.Required;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "plans")
public class Plan extends DomainEntity {

    @Column(name = "title")
    private String title;

    @Required
    @Before(otherFieldName = "expectedPlanEnd", minimumHours = 1)
    @Column(name = "expected_plan_start")
    private LocalDateTime expectedPlanStart;

    @Required
    @Column(name = "expected_plan_end")
    private LocalDateTime expectedPlanEnd;

    @Builder
    public Plan(Long id, String status, String note, String title, LocalDateTime expectedPlanStart, LocalDateTime expectedPlanEnd) {
        super(id, status, note);
        this.title = title;
        this.expectedPlanStart = expectedPlanStart;
        this.expectedPlanEnd = expectedPlanEnd;
    }

    @Override
    public PlanDTO toDTO() {
        return PlanDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .title(getTitle())
                .expectedPlanStart(getExpectedPlanStart())
                .expectedPlanEnd(getExpectedPlanEnd())
                .build();
    }

    @Override
    public String loggableString() {
        return null;
    }
}
