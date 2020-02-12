package com.ondrejkoula.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workout extends AbstractEntity {

    private String name;
    private String description;
    private String note;


    @OneToMany(mappedBy = "workout" ,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<WorkoutExerciseUnit> exerciseUnits;
}
