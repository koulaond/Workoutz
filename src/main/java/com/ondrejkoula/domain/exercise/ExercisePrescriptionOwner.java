package com.ondrejkoula.domain.exercise;

import com.ondrejkoula.service.validation.annotation.RequiredReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ExercisePrescriptionOwner extends Exercise {

    @RequiredReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    protected ExercisePrescription exercisePrescription;


    public ExercisePrescriptionOwner(Long id, String status, String note, ExercisePrescription exercisePrescription) {
        super(id, status, note);
        this.exercisePrescription = exercisePrescription;
    }
}
