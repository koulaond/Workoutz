package com.ondrejkoula.dto.exercise.weights;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.weights.SingleSet;
import com.ondrejkoula.domain.exercise.weights.Weights;
import com.ondrejkoula.dto.exercise.ExerciseDTO;
import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeightsDTO extends ExerciseDTO {

    private ExercisePrescriptionDTO exercisePrescription;

    private List<SingleSetDTO> sets;

    private Integer maxTimeSec;

    private Integer maxTimeMin;

    @Builder
    public WeightsDTO(Long id, String status, String note, ExercisePrescriptionDTO exercisePrescription,
                      List<SingleSetDTO> sets, Integer maxTimeSec, Integer maxTimeMin) {
        super(id, status, note);
        this.exercisePrescription = exercisePrescription;
        this.sets = sets;
        this.maxTimeSec = maxTimeSec;
        this.maxTimeMin = maxTimeMin;
    }

    @Override
    public Weights toDomain() {
        List<SingleSet> sets = CollectionUtils.isNotEmpty(getSets())
                ? getSets().stream().map(SingleSetDTO::toDomain).collect(Collectors.toList())
                : new ArrayList<>();

        return Weights.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .sets(sets)
                .maxTimeSec(getMaxTimeSec())
                .maxTimeMin(getMaxTimeMin())
                .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDomain() : null)
                .build();
    }
}
