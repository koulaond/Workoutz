package com.ondrejkoula.domain.superset;

import com.ondrejkoula.domain.ExercisePrescription;
import com.ondrejkoula.domain.IncorporatedItem;
import com.ondrejkoula.dto.SuperSetExerciseDTO;
import lombok.*;

import javax.persistence.*;

import static java.util.Objects.isNull;

/**
 * Embedded exercise in Super set series.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "super_set_exercises")
public class SuperSetExercise implements IncorporatedItem<SuperSet> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "status")
    private String status;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    @ManyToOne
    @JoinColumn(name = "super_set_id")
    private SuperSet superSet;

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

    private Integer position;

    @Override
    public SuperSet getParent() {
        return superSet;
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
