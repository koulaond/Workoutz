package com.ondrejkoula.domain.workout;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "exercises_workouts_assignments")
@AssociationOverrides({
        @AssociationOverride(name = "pk.workout",
        joinColumns = @JoinColumn(name = "workout_id")),
        @AssociationOverride(name = "pk.exercise",
        joinColumns = @JoinColumn(name = "exercise_id"))
})
public class ExerciseToWorkoutAssignment {

    @EmbeddedId
    private WorkoutExerciseId pk = new WorkoutExerciseId();

    private Integer position;

    @Builder
    public ExerciseToWorkoutAssignment(WorkoutExerciseId pk, Integer position) {
        this.pk = pk;
        this.position = position;
    }
}
