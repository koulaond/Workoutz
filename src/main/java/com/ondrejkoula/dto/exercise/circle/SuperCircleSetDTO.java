package com.ondrejkoula.dto.exercise.circle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSet;
import com.ondrejkoula.dto.AbstractDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;


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

    public static SuperCircleSetDTO from(@NotNull SuperCircleSet superCircleSet) {
        SuperCircleSetDTOBuilder builder = SuperCircleSetDTO.builder()
                .id(superCircleSet.getId())
                .status(superCircleSet.getStatus())
                .note(superCircleSet.getNote());

        if (isNotEmpty(superCircleSet.getSetExercises())) {
            builder.setExercises(superCircleSet.getSetExercises().stream()
                    .map(SuperCircleSetExerciseDTO::from)
                    .collect(toList()));
        }
        return builder.build();
    }
}
