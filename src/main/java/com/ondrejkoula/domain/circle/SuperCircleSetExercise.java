package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.ExercisePrescription;
import lombok.*;

import javax.persistence.*;

/**
 * Embedded exercise in circle set. DOes not exist as standalone exercise definition.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "super_circle_set_exercises")
public class SuperCircleSetExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    protected String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    @Column(name = "time_overriden_sec")
    private Integer timeOverriddenSec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_circle_set_id")
    private SuperCircleSet superCircleSet;

    @Column(name = "order_in_set")
    private Integer orderInSet;

}
