package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.DomainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "super_circle_sets")
public class SuperCircleSet extends DomainEntity {

    @OneToMany(mappedBy = "superCircleSet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderColumn(name = "position_in_set")
    private List<SuperCircleSetExercise> setExercises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_circle_id")
    private SuperCircle superCircle;

}
