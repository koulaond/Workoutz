package com.ondrejkoula.domain.exercise;


import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.workout.WorkoutExercise;
import com.ondrejkoula.dto.exercise.ExerciseDTO;
import com.ondrejkoula.service.validation.annotation.RequiredReferences;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Exercise extends DomainEntity {

    @RequiredReferences
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.exercise", cascade = CascadeType.ALL)
    private List<WorkoutExercise> workoutExercises;


    public Exercise() {
    }

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
