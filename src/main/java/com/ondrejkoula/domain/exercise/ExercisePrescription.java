package com.ondrejkoula.domain.exercise;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Override
    public String loggableString() {
        String exTypeLS = "null";
        if (!Objects.isNull(exerciseType)) {
            exTypeLS = exerciseType.loggableString();
        }
        return "Prescription details: [label: " + label +
                ", description: " + description +
                ", exercise type details: " + exTypeLS +
                "]";
    }

    @Override
    public ExercisePrescriptionDTO toDTO() {
        return ExercisePrescriptionDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .label(getLabel())
                .description(getDescription())
                .exerciseType(getExerciseType() != null ? getExerciseType().toDTO() : null)
                .build();
    }
}
