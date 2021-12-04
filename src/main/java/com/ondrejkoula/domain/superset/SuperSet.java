package com.ondrejkoula.domain.superset;

import com.ondrejkoula.domain.DomainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "super_sets")
public class SuperSet extends DomainEntity {

    @Column(name = "series_count")
    private Integer seriesCount;

    @Column(name = "series_count_goal")
    private Integer seriesCountGoal;

    @OneToMany(mappedBy = "superSet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderColumn(name = "order_in_super_set")
    private List<SuperSetExercise> seriesContent;

}
