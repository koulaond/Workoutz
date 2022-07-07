package com.ondrejkoula.domain.exercise.circle;

import com.ondrejkoula.domain.exercise.Exercise;
import com.ondrejkoula.dto.exercise.circle.CircleDTO;
import com.ondrejkoula.service.validation.annotation.Required;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "circles")
public class Circle extends Exercise {

    @Required
    @Column(name = "sets_count")
    private Integer setsCount;

    @Required
    @Column(name = "circles_in_count")
    private Integer circlesInCount;

    @Required
    @Column(name = "prepare_time")
    private Integer prepareTime;

    @Required
    @Column(name = "work_time")
    private Integer workTime;

    @Required
    @Column(name = "rest_time")
    private Integer restTime;

    @Required
    @Column(name = "time_between_sets")
    private Integer timeBetweenSets;

    @Required
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

    @Override
    public CircleDTO toDTO() {
       return CircleDTO.builder()
               .id(getId())
               .status(getStatus())
               .note(getNote())
               .setsCount(getSetsCount())
               .circlesInCount(getCirclesInCount())
               .prepareTime(getPrepareTime())
               .workTime(getWorkTime())
               .restTime(getRestTime())
               .timeBetweenSets(getTimeBetweenSets())
               .breatheOutTime(getBreatheOutTime())
               .build();
    }
}
