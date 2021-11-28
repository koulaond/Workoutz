package com.ondrejkoula.domain.superset;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.ExerciseValue;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "super_set")
public class SuperSet extends DomainEntity {

    @Embedded
    private ExerciseValue<Integer> seriesCount;

    @OneToMany(mappedBy = "superSet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuperSetExercise> seriesContent;

}
