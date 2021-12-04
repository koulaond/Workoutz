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
    protected long id;

    @Column(name = "status")
    protected String status;

    @OneToMany(mappedBy = "superCircleSet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderColumn(name = "position_in_set")
    private List<SuperCircleSetExercise> setExercises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_circle_id")
    private SuperCircle superCircle;

}
