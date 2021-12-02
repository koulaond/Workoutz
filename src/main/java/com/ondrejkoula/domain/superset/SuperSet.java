package com.ondrejkoula.domain.superset;

import com.ondrejkoula.domain.DomainEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "super_sets")
public class SuperSet extends DomainEntity {

    @Column(name = "series_count")
    private Integer seriesCount;

    @Column(name = "series_count_goal")
    private Integer seriesCountGoal;

    @OneToMany(mappedBy = "superSet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuperSetExercise> seriesContent;

}
