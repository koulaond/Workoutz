package com.ondrejkoula.domain.superset;

import com.ondrejkoula.domain.DomainEntity;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "super_set")
public class SuperSet extends DomainEntity {

    @Column(name = "series_count")
    private Integer seriesCount;

    @Column(name = "series_count_goal")
    private Integer seriesCountGoal;

    @OneToMany(mappedBy = "superSet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuperSetExercise> seriesContent;

}
