package com.ondrejkoula.dto.exercise.circle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.circle.Circle;
import com.ondrejkoula.dto.exercise.ExerciseDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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

    public static CircleDTO from(@NotNull Circle circle) {
        return CircleDTO.builder()
                .id(circle.getId())
                .status(circle.getStatus())
                .note(circle.getNote())
                .setsCount(circle.getSetsCount())
                .circlesInCount(circle.getCirclesInCount())
                .prepareTime(circle.getPrepareTime())
                .workTime(circle.getWorkTime())
                .restTime(circle.getRestTime())
                .timeBetweenSets(circle.getTimeBetweenSets())
                .breatheOutTime(circle.getBreatheOutTime())
                .build();
    }
}
