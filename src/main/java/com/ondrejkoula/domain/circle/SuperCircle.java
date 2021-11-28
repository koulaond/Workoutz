package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.DomainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
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

    private Integer prepareTime;

    private Integer workTime;

    private Integer restTime;

    private Integer timeBetweenSets;

    private Integer breatheOutTime;

    @OneToMany(mappedBy = "circle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CircleSet> definedSets;

}
