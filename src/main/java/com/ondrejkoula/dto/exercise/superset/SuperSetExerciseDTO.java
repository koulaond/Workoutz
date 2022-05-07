package com.ondrejkoula.dto.exercise.superset;

import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.dto.AbstractDTO;
import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuperSetExerciseDTO extends AbstractDTO {

    private ExercisePrescriptionDTO exercisePrescription;

    private Integer repetitionsCount;

    private Integer repetitionsCountGoal;

    private Integer weight;

    private Integer weightGoal;

    private Integer maxTimeSec;

    private Integer maxTimeMin;

    private Integer position;

    @Builder
    public SuperSetExerciseDTO(Long id, String status, String note, ExercisePrescriptionDTO exercisePrescription,
                               Integer repetitionsCount, Integer repetitionsCountGoal, Integer weight,
                               Integer weightGoal, Integer maxTimeSec, Integer maxTimeMin, Integer position) {
        super(id, status, note);
        this.exercisePrescription = exercisePrescription;
        this.repetitionsCount = repetitionsCount;
        this.repetitionsCountGoal = repetitionsCountGoal;
        this.weight = weight;
        this.weightGoal = weightGoal;
        this.maxTimeSec = maxTimeSec;
        this.maxTimeMin = maxTimeMin;
        this.position = position;
    }

    @Override
    public SuperSetExercise toDomain() {
        return SuperSetExercise.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .repetitionsCount(getRepetitionsCount())
                .repetitionsCountGoal(getRepetitionsCountGoal())
                .weight(getWeight())
                .weightGoal(getWeightGoal())
                .maxTimeSec(getMaxTimeSec())
                .maxTimeMin(getMaxTimeMin())
                .position(getPosition())
                .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDomain() : null)
                .build();
    }
}
