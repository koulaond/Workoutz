package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.DomainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "circle_sets")
public class CircleSet extends DomainEntity {

    @OneToMany(mappedBy = "circleSet")
    private List<CircleSetExercise> setExercises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circle_id")
    private SuperCircle circle;

}
