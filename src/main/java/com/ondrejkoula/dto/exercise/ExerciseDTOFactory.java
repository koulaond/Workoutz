package com.ondrejkoula.dto.exercise;

import com.ondrejkoula.domain.exercise.Condition;
import com.ondrejkoula.domain.exercise.Exercise;
import com.ondrejkoula.domain.exercise.HighIntensityInterval;
import com.ondrejkoula.domain.exercise.SetsAndRepetitions;
import com.ondrejkoula.domain.exercise.circle.Circle;
import com.ondrejkoula.domain.exercise.circle.SuperCircle;
import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.dto.exercise.circle.CircleDTO;
import com.ondrejkoula.dto.exercise.circle.SuperCircleDTO;
import com.ondrejkoula.dto.exercise.superset.SuperSetDTO;
import com.ondrejkoula.exception.InternalException;

public class ExerciseDTOFactory {

    public ExerciseDTO getDTOForExercise(Exercise exercise) {
        if (exercise instanceof SetsAndRepetitions) {
            return SetsAndRepetitionsDTO.from((SetsAndRepetitions) exercise);

        } else if (exercise instanceof SuperSet) {
            return SuperSetDTO.from((SuperSet) exercise);

        } else if (exercise instanceof Circle) {
            return CircleDTO.from((Circle) exercise);

        } else if (exercise instanceof SuperCircle) {
            return SuperCircleDTO.from((SuperCircle) exercise);

        } else if (exercise instanceof Condition) {
            return ConditionDTO.from((Condition) exercise);

        } else if (exercise instanceof HighIntensityInterval) {
            return HighIntensityIntervalDTO.from((HighIntensityInterval) exercise);
        }
        throw new InternalException("Unsupported exercise type. Cannot convert to DTO.", "unsupportedExerciseType");
    }
}
