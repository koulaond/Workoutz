package com.ondrejkoula.domain.exercise;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.ExercisePrescriptionDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exercise_prescriptions")
public class ExercisePrescription extends DomainEntity {

    @Column(name = "label")
    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_type_id")
    private ExerciseType exerciseType;

    @Column(name = "description")
    private String description;

    @Builder
    public ExercisePrescription(Long id, String status, String note, String label, ExerciseType exerciseType, String description) {
        super(id, status, note);
        this.label = label;
        this.exerciseType = exerciseType;
        this.description = description;
    }

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

    @Override
    public String loggableString() {
        String exTypeLS = "null";
        if (!Objects.isNull(exerciseType)) {
            exTypeLS = exerciseType.loggableString();
        }
        String stringBuilder = "Prescription details: [label: " + label +
                ", description: " + description +
                ", exercise type details: " + exTypeLS +
                "]";
        return stringBuilder;
    }
}
