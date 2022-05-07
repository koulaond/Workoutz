package com.ondrejkoula.dto.exercise.circle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.circle.Circle;
import com.ondrejkoula.dto.exercise.ExerciseDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CircleDTO extends ExerciseDTO {

    private Integer setsCount;

    private Integer circlesInCount;

    private Integer prepareTime;

    private Integer workTime;

    private Integer restTime;

    private Integer timeBetweenSets;

    private Integer breatheOutTime;

    @Builder
    public CircleDTO(Long id, String status, String note, Integer setsCount, Integer circlesInCount, Integer prepareTime,
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
    public Circle toDomain() {
        return Circle.builder()
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
