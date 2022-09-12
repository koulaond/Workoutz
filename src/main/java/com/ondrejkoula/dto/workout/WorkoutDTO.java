package com.ondrejkoula.dto.workout;

import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.dto.AbstractDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkoutDTO extends AbstractDTO {

    private String label;

    private Integer expectedDurationInMins;

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
