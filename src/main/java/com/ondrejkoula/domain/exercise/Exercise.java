package com.ondrejkoula.domain.exercise;


import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.workout.ExerciseToWorkoutAssignment;
import com.ondrejkoula.dto.exercise.ExerciseDTO;
import com.ondrejkoula.service.validation.annotation.RequiredReferences;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Exercise extends DomainEntity {

    @RequiredReferences
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.exercise", cascade = CascadeType.ALL)
    protected List<ExerciseToWorkoutAssignment> exerciseToWorkoutAssignments;

    public Exercise(Long id, String status, String note) {
        super(id, status, note);
    }

    @Override
    public String loggableString() {
        return "";
    }

    @Override
    public abstract ExerciseDTO toDTO();
}
