package com.ondrejkoula.dto.workout;

import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.dto.AbstractDTO;
import lombok.Builder;
import lombok.Data;

@Data
public class WorkoutDTO extends AbstractDTO {

    private final String label;

    private final Integer expectedDurationInMins;

    @Builder
    public WorkoutDTO(Long id, String status, String note, String label, Integer expectedDurationInMins) {
        super(id, status, note);
        this.label = label;
        this.expectedDurationInMins = expectedDurationInMins;
    }

    @Override
    public Workout toDomain() {

        return Workout.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .label(getLabel())
                .expectedDurationInMins(getExpectedDurationInMins())
                .build();
    }
}
