package com.ondrejkoula.dto.exercise;

import com.ondrejkoula.domain.exercise.Exercise;
import com.ondrejkoula.domain.exercise.SetsAndRepetitions;
import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.dto.exercise.superset.SuperSetDTO;
import com.ondrejkoula.exception.InternalException;

public class ExerciseDTOFactory {
    public ExerciseDTO getDTOForExercise(Exercise exercise) {
        if (exercise instanceof SetsAndRepetitions) {
            return SetsAndRepetitionsDTO.from((SetsAndRepetitions) exercise);

        } else if (exercise instanceof SuperSet) {
            return SuperSetDTO.from((SuperSet) exercise);
        }
        throw new InternalException("Unsupported exercise type. Cannot convert to DTO.", "unsupportedExerciseType");
    }
}
