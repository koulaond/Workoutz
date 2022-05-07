package com.ondrejkoula.dto.exercise.superset;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.dto.exercise.ExerciseDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperSetDTO extends ExerciseDTO {

    private Integer seriesCount;

    private Integer seriesCountGoal;

    private List<SuperSetExerciseDTO> seriesContent;

    @Builder
    public SuperSetDTO(Long id, String status, String note, Integer seriesCount,
                       Integer seriesCountGoal, List<SuperSetExerciseDTO> seriesContent) {
        super(id, status, note);
        this.seriesCount = seriesCount;
        this.seriesCountGoal = seriesCountGoal;
        this.seriesContent = seriesContent;
    }

    public static SuperSetDTO from(@NotNull SuperSet superSet) {
        SuperSetDTOBuilder builder = SuperSetDTO.builder()
                .id(superSet.getId())
                .status(superSet.getStatus())
                .note(superSet.getNote())
                .seriesCount(superSet.getSeriesCount())
                .seriesCountGoal(superSet.getSeriesCountGoal());

        if (CollectionUtils.isNotEmpty(superSet.getSeriesContent())) {
            builder.seriesContent(superSet.getSeriesContent()
                    .stream()
                    .map(SuperSetExerciseDTO::from)
                    .collect(Collectors.toList()));
        }
        return builder.build();
    }

    @Override
    public SuperSet toDomain() {
        List<SuperSetExercise> seriesContentAsDomain = CollectionUtils.isNotEmpty(seriesContent)
                ? seriesContent.stream().map(SuperSetExerciseDTO::toDomain).collect(Collectors.toList())
                : new ArrayList<>();

        return SuperSet.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .seriesCount(getSeriesCount())
                .seriesCountGoal(getSeriesCountGoal())
                .seriesContent(seriesContentAsDomain)
                .build();
    }
}
