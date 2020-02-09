package com.ondrejkoula.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Workout extends AbstractEntity {

    private String name;
    private String description;
    private String note;

    @OneToMany
    private List<WorkoutExerciseUnit> exerciseUnits;
}
