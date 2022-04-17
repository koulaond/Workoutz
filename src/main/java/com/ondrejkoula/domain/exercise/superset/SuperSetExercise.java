package com.ondrejkoula.domain.exercise.superset;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.domain.IncorporatedItem;
import com.ondrejkoula.dto.exercise.superset.SuperSetExerciseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

import static java.util.Objects.isNull;

/**
 * Embedded exercise in Super set series.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "super_set_exercises")
public class SuperSetExercise extends IncorporatedItem<SuperSet> {

    @ManyToOne
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

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
    public SuperSetExercise(Long id, String status, String note, SuperSet parent, Integer position,
                            ExercisePrescription exercisePrescription, Integer repetitionsCount, Integer repetitionsCountGoal,
                            Integer weight, Integer weightGoal, Integer maxTimeSec, Integer maxTimeMin) {
        super(id, status, note, parent, position);
        this.exercisePrescription = exercisePrescription;
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

    public static SuperSetExercise from(SuperSetExerciseDTO dto) {
        SuperSetExerciseBuilder builder = SuperSetExercise.builder()
                .status(dto.getStatus())
                .note(dto.getNote())
                .repetitionsCount(dto.getRepetitionsCount())
                .repetitionsCountGoal(dto.getRepetitionsCountGoal())
                .weight(dto.getWeight())
                .weightGoal(dto.getWeightGoal())
                .maxTimeSec(dto.getMaxTimeSec())
                .maxTimeMin(dto.getMaxTimeMin())
                .position(dto.getPosition());

        if (!isNull(dto.getExercisePrescription())) {
            builder.exercisePrescription(ExercisePrescription.from(dto.getExercisePrescription()));
        }
        return builder.build();
    }

}
