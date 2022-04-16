package com.ondrejkoula.domain.workout;

import com.ondrejkoula.domain.exercise.Exercise;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class WorkoutExerciseId implements Serializable {

    @ManyToOne
    private Workout workout;

    @ManyToOne
    private Exercise exercise;

    @Builder
    public WorkoutExerciseId(Workout workout, Exercise exercise) {
        this.workout = workout;
        this.exercise = exercise;
    }
}
