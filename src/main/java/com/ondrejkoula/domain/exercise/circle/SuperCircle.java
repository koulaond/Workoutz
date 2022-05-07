package com.ondrejkoula.domain.exercise.circle;

import com.ondrejkoula.domain.exercise.Exercise;
import com.ondrejkoula.dto.exercise.circle.SuperCircleDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static java.util.Objects.isNull;

/**
 * Adjusted circle training where each set has exercises specified.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "super_circles")
public class SuperCircle extends Exercise {

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

    @Column(name = "sets_count")
    private Integer setsCount;

    @OneToOne(mappedBy = "superCircle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private SuperCircleSet set;

    @Builder
    public SuperCircle(Long id, String status, String note, Integer prepareTime, Integer workTime, Integer restTime,
                       Integer timeBetweenSets, Integer breatheOutTime, Integer setsCount, SuperCircleSet set) {
        super(id, status, note);
        this.prepareTime = prepareTime;
        this.workTime = workTime;
        this.restTime = restTime;
        this.timeBetweenSets = timeBetweenSets;
        this.breatheOutTime = breatheOutTime;
        this.setsCount = setsCount;
        this.set = set;
    }

    @Override
    public String loggableString() {
        String setDefinition = !isNull(set) ? set.loggableString() : "null";
        return "Circle exercise [sets count: " + setsCount + ", prepare time: " + prepareTime + ", set definition: "
        + setDefinition + "...]";
    }

    @Override
    public SuperCircleDTO toDTO() {
        return SuperCircleDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .setsCount(getSetsCount())
                .prepareTime(getPrepareTime())
                .workTime(getWorkTime())
                .restTime(getRestTime())
                .timeBetweenSets(getTimeBetweenSets())
                .breatheOutTime(getBreatheOutTime())
                .set(getSet() != null ? getSet().toDTO() : null)
                .build();
    }
}
