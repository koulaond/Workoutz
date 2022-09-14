package com.ondrejkoula.domain;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.domain.exercise.ExercisePrescriptionOwner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class CompositionChildExercise<PARENT extends DomainEntity> extends ExercisePrescriptionOwner {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    protected PARENT parent;

    @Column(name = "position")
    protected Integer position;

    public CompositionChildExercise(Long id, String status, String note, ExercisePrescription exercisePrescription, PARENT parent, Integer position) {
        super(id, status, note, exercisePrescription);
        this.parent = parent;
        this.position = position;
    }
}
