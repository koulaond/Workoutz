package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.DomainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Adjusted circle training where each set has exercises specified.
 */
@Getter
@Setter
@Table(name = "super_circle")
public class SuperCircle extends DomainEntity {

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

    @OneToMany(mappedBy = "circle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CircleSet> definedSets;

}
