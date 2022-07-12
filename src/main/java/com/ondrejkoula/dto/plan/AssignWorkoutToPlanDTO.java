package com.ondrejkoula.dto.plan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignWorkoutToPlanDTO {

    private Long planId;

    private Long workoutId;

    private LocalDateTime dateAndTimeScheduled;
}
