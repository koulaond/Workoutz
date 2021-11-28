package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.DomainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@Table(name = "circle")
public class Circle extends DomainEntity {

    private Integer setsCount;

    private Integer circlesInCount;

    private Integer prepareTime;

    private Integer workTime;

    private Integer restTime;

    private Integer timeBetweenSets;

    private Integer breatheOutTime;
}
