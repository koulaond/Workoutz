package com.ondrejkoula.domain.superset;

import com.ondrejkoula.domain.ExercisePrescription;
import com.ondrejkoula.domain.HasOrder;
import lombok.*;

import javax.persistence.*;

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
public class SuperSetExercise implements HasOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(name = "status")
    protected String status;

    @ManyToOne
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    @ManyToOne
    @JoinColumn(name = "super_set_id")
    private SuperSet superSet;

    @Column(name = "order_in_set")
    private Integer orderInSet;

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

    @Override
    public Integer getOrder() {
        return orderInSet;
    }
}
