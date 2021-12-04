package com.ondrejkoula.domain.circle;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Adjusted circle training where each set has exercises specified.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "super_circles")
public class SuperCircle  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(name = "status")
    protected String status;

    @Column(name = "prepare_time")
    private Integer prepareTime;

    @Column(name = "work_time")
    private Integer workTime;

    @Column(name = "rest_time")
    private Integer restTime;

    @Column(name = "time_between_sets")
    private Integer timeBetweenSets;

    @Column(name = "breathe_out_time")
    private Integer breatheOutTime;

    @OneToMany(mappedBy = "superCircle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderColumn(name = "position_in_circle")
    private List<SuperCircleSet> definedSets;

}
