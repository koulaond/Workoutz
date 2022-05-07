package com.ondrejkoula.dto.exercise.circle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSet;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.dto.AbstractDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperCircleSetDTO extends AbstractDTO {

    private List<SuperCircleSetExerciseDTO> setExercises;

    @Builder
    public SuperCircleSetDTO(Long id, String status, String note, List<SuperCircleSetExerciseDTO> setExercises) {
        super(id, status, note);
        this.setExercises = setExercises;
    }

    @Override
    public SuperCircleSet toDomain() {
        List<SuperCircleSetExercise> setExercisesAsDomain = CollectionUtils.isNotEmpty(setExercises)
                ? setExercises.stream().map(SuperCircleSetExerciseDTO::toDomain).collect(toList())
                : new ArrayList<>();

        return SuperCircleSet.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .setExercises(setExercisesAsDomain)
                .build();
    }
}
