package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "exercise_name", unique = true)
    private String name;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "exercise_muscles",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "muscles_id")
    )
    private List<Muscles> muscles;

    @ManyToOne
    private ExerciseType exerciseType;

    public Exercise(String name, String description, List<Muscles> muscles, ExerciseType exerciseType) {
        this.name = name;
        this.description = description;
        this.muscles = muscles;
        this.exerciseType = exerciseType;
    }
}
