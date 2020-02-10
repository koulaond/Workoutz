package com.ondrejkoula.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workout extends AbstractEntity {

    private String name;
    private String description;
    private String note;

    @OneToMany
    private List<WorkoutExerciseUnit> exerciseUnits;
}
