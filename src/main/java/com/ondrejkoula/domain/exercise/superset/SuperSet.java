package com.ondrejkoula.domain.exercise.superset;

import com.ondrejkoula.domain.Composite;
import com.ondrejkoula.domain.exercise.Exercise;
import com.ondrejkoula.dto.exercise.superset.SuperSetDTO;
import com.ondrejkoula.dto.exercise.superset.SuperSetExerciseDTO;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@Table(name = "super_sets")
public class SuperSet extends Exercise implements Composite<SuperSet> {

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

    @Override
    public SuperSetDTO toDTO() {
        List<SuperSetExerciseDTO> seriesContentDTOs = CollectionUtils.isNotEmpty(seriesContent)
                ? seriesContent.stream().map(SuperSetExercise::toDTO).collect(Collectors.toList())
                : new ArrayList<>();

        return SuperSetDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .seriesCount(getSeriesCount())
                .seriesCountGoal(getSeriesCountGoal())
                .seriesContent(seriesContentDTOs)
                .build();
    }

    @Override
    public List<SuperSetExercise> getChildren() {
        return seriesContent;
    }
}
