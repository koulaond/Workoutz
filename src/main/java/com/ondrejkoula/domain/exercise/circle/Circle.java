package com.ondrejkoula.domain.exercise.circle;

import com.ondrejkoula.domain.DomainEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "circles")
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

    @Builder
    public Circle(Long id, String status, String note, Integer setsCount, Integer circlesInCount, Integer prepareTime,
                  Integer workTime, Integer restTime, Integer timeBetweenSets, Integer breatheOutTime) {
        super(id, status, note);
        this.setsCount = setsCount;
        this.circlesInCount = circlesInCount;
        this.prepareTime = prepareTime;
        this.workTime = workTime;
        this.restTime = restTime;
        this.timeBetweenSets = timeBetweenSets;
        this.breatheOutTime = breatheOutTime;
    }

    @Override
    public String loggableString() {
        return "Circle exercise [sets count: " + setsCount + ", circles count: "
                + circlesInCount + ", prepare time: " + prepareTime + ", ...]";
    }
}
