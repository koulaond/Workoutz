package com.ondrejkoula.domain.workout;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "workouts_exercises")
@AssociationOverrides({
        @AssociationOverride(name = "pk.workout",
        joinColumns = @JoinColumn(name = "workout_id")),
        @AssociationOverride(name = "pk.exercise",
        joinColumns = @JoinColumn(name = "exercise_id"))
})
public class WorkoutExercise {

    @EmbeddedId
    private WorkoutExerciseId pk = new WorkoutExerciseId();

    private Integer position;

    @Builder
    public WorkoutExercise(WorkoutExerciseId pk, Integer position) {
        this.pk = pk;
        this.position = position;
    }
}
