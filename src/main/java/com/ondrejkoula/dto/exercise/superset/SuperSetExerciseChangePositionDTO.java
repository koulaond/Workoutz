package com.ondrejkoula.dto.exercise.superset;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuperSetExerciseChangePositionDTO {

    private Long id;

    private Integer newPosition;

}
