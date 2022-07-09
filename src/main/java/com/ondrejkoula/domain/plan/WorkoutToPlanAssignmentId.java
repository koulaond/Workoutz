package com.ondrejkoula.domain.plan;

import com.ondrejkoula.domain.workout.Workout;
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
public class WorkoutToPlanAssignmentId implements Serializable {

    @ManyToOne
    private Workout workout;

    @ManyToOne
    private Plan plan;

}
