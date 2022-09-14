package com.ondrejkoula.domain.exercise.superset;

import com.ondrejkoula.domain.CompositionChildExercise;
import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.dto.exercise.superset.SuperSetExerciseDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Embedded exercise in Super set series.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "super_set_exercises")
public class SuperSetExercise extends CompositionChildExercise<SuperSet> {
    
    // Repetitions values
    @Column(name = "repetitions_count")
    private Integer repetitionsCount;

    @Column(name = "repetitions_count_goal")
    private Integer repetitionsCountGoal;

    // Weight values
    @Column(name = "weight")
    private Integer weight;

    @Column(name = "weight_goal")
    private Integer weightGoal;

    // Time values
    @Column(name = "max_time_sec")
    private Integer maxTimeSec;

    @Column(name = "max_time_min")
    private Integer maxTimeMin;

    @Builder
    public SuperSetExercise(Long id, String status, String note, ExercisePrescription exercisePrescription, SuperSet parent,
                            Integer position, Integer repetitionsCount, Integer repetitionsCountGoal, Integer weight,
                            Integer weightGoal, Integer maxTimeSec, Integer maxTimeMin) {
        super(id, status, note, exercisePrescription, parent, position);
        this.repetitionsCount = repetitionsCount;
        this.repetitionsCountGoal = repetitionsCountGoal;
        this.weight = weight;
        this.weightGoal = weightGoal;
        this.maxTimeSec = maxTimeSec;
        this.maxTimeMin = maxTimeMin;
    }

    @Override
    public String loggableString() {
        String exPrescLoggableString = "null";
        if (!Objects.isNull(exercisePrescription)) {
            exPrescLoggableString = exercisePrescription.loggableString();
        }

        return "Super set exercise item: [position: " + position + "prescription details: " + exPrescLoggableString + "]";
    }
    
    @Override
    public SuperSetExerciseDTO toDTO() {
       return SuperSetExerciseDTO.builder()
               .id(getId())
               .status(getStatus())
               .note(getNote())
               .position(getPosition())
               .repetitionsCount(getRepetitionsCount())
               .repetitionsCountGoal(getRepetitionsCountGoal())
               .weight(getWeight())
               .weightGoal(getWeightGoal())
               .maxTimeSec(getMaxTimeSec())
               .maxTimeMin(getMaxTimeMin())
               .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDTO() : null)
               .build();

    }
}
