package com.ondrejkoula.domain.exercise.superset;

import com.ondrejkoula.domain.exercise.Exercise;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "super_sets")
public class SuperSet extends Exercise {

    @Column(name = "series_count")
    private Integer seriesCount;

    @Column(name = "series_count_goal")
    private Integer seriesCountGoal;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SuperSetExercise> seriesContent;

    @Builder
    public SuperSet(Long id, String status, String note, Integer seriesCount,
                    Integer seriesCountGoal, List<SuperSetExercise> seriesContent) {
        super(id, status, note);
        this.seriesCount = seriesCount;
        this.seriesCountGoal = seriesCountGoal;
        this.seriesContent = seriesContent;
    }

    public void setSeriesContent(List<SuperSetExercise> newContent) {
        this.seriesContent.clear();
        if (!CollectionUtils.isEmpty(newContent)) {
            this.seriesContent.addAll(newContent);
        }
    }

    @Override
    public String loggableString() {
        String contentLoggableString = "null";
        if (CollectionUtils.isNotEmpty(seriesContent)) {
            contentLoggableString = seriesContent.stream()
                    .map(SuperSetExercise::loggableString)
                    .collect(Collectors.joining(";"));
        }
        return "Super set [series count: " + seriesCount + ", series count goal: "
                + seriesCountGoal + ", exercises: ["+ contentLoggableString + "]]";
    }
}
