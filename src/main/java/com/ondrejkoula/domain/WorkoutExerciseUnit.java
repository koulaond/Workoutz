package com.ondrejkoula.domain;

import com.ondrejkoula.domain.preset.ExercisePreset;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkoutExerciseUnit extends AbstractEntity {

    private Integer position;

    @ManyToOne
    @JoinColumn(name = "exercise_preset_id")
    private ExercisePreset exercisePreset;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;
}
