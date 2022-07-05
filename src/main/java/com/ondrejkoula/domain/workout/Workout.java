package com.ondrejkoula.domain.workout;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.workout.WorkoutDTO;
import com.ondrejkoula.service.validation.annotation.Required;
import com.ondrejkoula.service.validation.annotation.RequiredReferences;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "workouts")
public class Workout extends DomainEntity {

    @Required
    @Column(name = "label")
    private String label;

    @Column(name = "expected_duration")
    private Integer expectedDuration;

    @RequiredReferences
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.workout", cascade = CascadeType.ALL)
    private List<WorkoutExercise> workoutExercises;

    @Builder
    public Workout(Long id, String status, String note, String label, Integer expectedDuration, List<WorkoutExercise> workoutExercises) {
        super(id, status, note);
        this.label = label;
        this.expectedDuration = expectedDuration;
        this.workoutExercises = workoutExercises;
    }

    @Override
    public String loggableString() {
        return null;
    }

    @Override
    public WorkoutDTO toDTO() {
        return null;
    }
}
