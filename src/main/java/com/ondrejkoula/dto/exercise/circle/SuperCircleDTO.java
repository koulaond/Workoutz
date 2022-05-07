package com.ondrejkoula.dto.exercise.circle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.circle.SuperCircle;
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
public class SuperCircleDTO extends ExerciseDTO {

    private Integer prepareTime;

    private Integer workTime;

    private Integer restTime;

    private Integer timeBetweenSets;

    private Integer breatheOutTime;

    private Integer setsCount;

    private SuperCircleSetDTO set;

    @Builder
    public SuperCircleDTO(Long id, String status, String note, Integer prepareTime, Integer workTime, Integer restTime,
                          Integer timeBetweenSets, Integer breatheOutTime, Integer setsCount, SuperCircleSetDTO set) {
        super(id, status, note);
        this.prepareTime = prepareTime;
        this.workTime = workTime;
        this.restTime = restTime;
        this.timeBetweenSets = timeBetweenSets;
        this.breatheOutTime = breatheOutTime;
        this.setsCount = setsCount;
        this.set = set;
    }

    public static SuperCircleDTO from(@NotNull SuperCircle superCircle) {
        SuperCircleDTOBuilder builder = SuperCircleDTO.builder()
                .id(superCircle.getId())
                .status(superCircle.getStatus())
                .note(superCircle.getNote())
                .prepareTime(superCircle.getPrepareTime())
                .workTime(superCircle.getWorkTime())
                .restTime(superCircle.getRestTime())
                .timeBetweenSets(superCircle.getTimeBetweenSets())
                .breatheOutTime(superCircle.getBreatheOutTime())
                .setsCount(superCircle.getSetsCount());

        if (superCircle.getSet() != null) {
            builder.set(SuperCircleSetDTO.from(superCircle.getSet()));
        }

        return builder.build();
    }

    @Override
    public SuperCircle toDomain() {
        return SuperCircle.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .setsCount(getSetsCount())
                .prepareTime(getPrepareTime())
                .workTime(getWorkTime())
                .restTime(getRestTime())
                .timeBetweenSets(getTimeBetweenSets())
                .breatheOutTime(getBreatheOutTime())
                .set(getSet() != null ? getSet().toDomain() : null)
                .build();
    }
}
