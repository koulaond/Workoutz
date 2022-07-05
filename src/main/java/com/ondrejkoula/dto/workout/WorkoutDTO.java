package com.ondrejkoula.dto.workout;

import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.dto.AbstractDTO;
import lombok.Builder;
import lombok.Data;

@Data
public class WorkoutDTO extends AbstractDTO {

    private final String label;

    private final Integer expectedDuration;

    @Builder
    public WorkoutDTO(Long id, String status, String note, String label, Integer expectedDuration) {
        super(id, status, note);
        this.label = label;
        this.expectedDuration = expectedDuration;
    }

    @Override
    public Workout toDomain() {

        return null;
    }
}
