package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.DomainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "circle")
public class Circle extends DomainEntity {

    @Column(name = "sets_count")
    private Integer setsCount;

    @Column(name = "circles_in_count")
    private Integer circlesInCount;

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
}
