package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.DomainEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode
@Document
public class Circle extends DomainEntity {

    private Integer setsCount;

    private Integer circlesInCount;

    private Integer prepareTime;

    private Integer workTime;

    private Integer restTime;

    private Integer timeBetweenSets;

    private Integer breatheOutTime;
}
