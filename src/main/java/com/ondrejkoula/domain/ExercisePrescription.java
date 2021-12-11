package com.ondrejkoula.domain;

import com.ondrejkoula.dto.ExercisePrescriptionDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercise_prescriptions")
public class ExercisePrescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    protected String status;

    @Column(name = "label")
    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_type_id")
    private ExerciseType exerciseType;

    @Column(name = "description")
    private String description;

    public static ExercisePrescription from(ExercisePrescriptionDTO dto) {
        ExercisePrescriptionBuilder builder = ExercisePrescription.builder()
                .status(dto.getStatus())
                .label(dto.getLabel())
                .description(dto.getDescription());

        if (!Objects.isNull(dto.getExerciseType())) {
            builder.exerciseType(ExerciseType.from(dto.getExerciseType()));
        }
        return builder.build();
    }
}
