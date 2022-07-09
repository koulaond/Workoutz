package com.ondrejkoula.dto.plan;

import com.ondrejkoula.domain.plan.Plan;
import com.ondrejkoula.dto.AbstractDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlanDTO extends AbstractDTO {

    private String title;

    private LocalDateTime expectedPlanStart;

    private LocalDateTime expectedPlanEnd;

    @Builder
    public PlanDTO(Long id,
                   String status,
                   String note,
                   String title,
                   LocalDateTime expectedPlanStart,
                   LocalDateTime expectedPlanEnd) {

        super(id, status, note);
        this.title = title;
        this.expectedPlanStart = expectedPlanStart;
        this.expectedPlanEnd = expectedPlanEnd;
    }

    @Override
    public Plan toDomain() {
        return Plan.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .title(getTitle())
                .expectedPlanStart(getExpectedPlanStart())
                .expectedPlanEnd(getExpectedPlanEnd())
                .build();
    }
}
