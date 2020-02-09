package com.ondrejkoula.domain;

import com.ondrejkoula.domain.preset.ExercisePreset;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity
public class WorkoutExerciseUnit {

    @EmbeddedId
    private WorkoutExerciseUnitId id;

    @ManyToOne
    @JoinColumn(name = "exercise_preset_id")
    private ExercisePreset exercisePreset;

    public WorkoutExerciseUnit() {
    }

    public WorkoutExerciseUnit(WorkoutExerciseUnitId id, ExercisePreset exercisePreset) {
        this.id = id;
        this.exercisePreset = exercisePreset;
    }

    @Getter
    @Setter
    @Builder
    @Embeddable
    public static class WorkoutExerciseUnitId implements Serializable {

        @ManyToOne
        @JoinColumn(name = "workout_id", referencedColumnName = "id")
        private Workout workout;

        private Integer position;

        public WorkoutExerciseUnitId() {
        }

        public WorkoutExerciseUnitId(Workout workout, Integer position) {
            this.workout = workout;
            this.position = position;
        }
    }
}
