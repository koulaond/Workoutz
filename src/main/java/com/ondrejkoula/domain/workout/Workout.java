package com.ondrejkoula.domain.workout;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.workout.WorkoutDTO;
import com.ondrejkoula.service.validation.annotation.Required;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "workouts")
public class Workout extends DomainEntity {

    @Required
    @Column(name = "label")
    private String label;

    @Column(name = "expected_duration_in_mins")
    private Integer expectedDurationInMins;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.workout", cascade = CascadeType.ALL)
    private List<ExerciseToWorkoutAssignment> exerciseToWorkoutAssignments;

    @Builder
    public Workout(Long id,
                   String status,
                   String note,
                   String label,
                   Integer expectedDurationInMins,
                   List<ExerciseToWorkoutAssignment> exerciseToWorkoutAssignments) {
        super(id, status, note);
        this.label = label;
        this.expectedDurationInMins = expectedDurationInMins;
        this.exerciseToWorkoutAssignments = exerciseToWorkoutAssignments;
    }

    @Override
    public WorkoutDTO toDTO() {
        return WorkoutDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .label(getLabel())
                .expectedDurationInMins(getExpectedDurationInMins())
                .build();
    }

    @Override
    public String loggableString() {
        return null;
    }

}
