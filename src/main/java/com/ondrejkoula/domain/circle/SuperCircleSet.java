package com.ondrejkoula.domain.circle;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "super_circle_sets")
public class SuperCircleSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    protected String status;

    @OneToMany(mappedBy = "superCircleSet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SuperCircleSetExercise> setExercises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_circle_id")
    private SuperCircle superCircle;

    @Column(name = "order_in_cycle")
    private Integer orderInCycle;

}
