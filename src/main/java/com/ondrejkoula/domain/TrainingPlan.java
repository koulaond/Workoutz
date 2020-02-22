package com.ondrejkoula.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingPlan extends AbstractEntity {

    private String name;
    private String description;
    private String note;
    private Integer weeks;
    private Integer daysPerWeek;
    private Integer phases;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trainingPlan", cascade= CascadeType.ALL)
    private List<TrainingPlanWorkout> workouts;
}
