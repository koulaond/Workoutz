package com.ondrejkoula.domain.exercise.weights;

import com.ondrejkoula.domain.exercise.Exercise;
import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.dto.exercise.weights.SingleSetDTO;
import com.ondrejkoula.dto.exercise.weights.WeightsDTO;
import com.ondrejkoula.service.validation.annotation.Required;
import com.ondrejkoula.service.validation.annotation.RequiredEmbedded;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
@Table(name = "weights")
public class Weights extends Exercise {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    @RequiredEmbedded
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "weights_sets", joinColumns = @JoinColumn(name = "weights_id"))
    private List<SingleSet> sets;

    // Time values
    @Required
    @Column(name = "max_time_sec")
    private Integer maxTimeSec;

    @Required
    @Column(name = "max_time_min")
    private Integer maxTimeMin;

    @Builder
    public Weights(Long id, String status, String note, ExercisePrescription exercisePrescription,
                   List<SingleSet> sets, Integer maxTimeSec, Integer maxTimeMin) {
        super(id, status, note);
        this.exercisePrescription = exercisePrescription;
        this.sets = sets;
        this.maxTimeSec = maxTimeSec;
        this.maxTimeMin = maxTimeMin;
    }

    @Override
    public String loggableString() {

        String exPrescLoggableString = "null";
        if (!Objects.isNull(exercisePrescription)) {
            exPrescLoggableString = exercisePrescription.loggableString();
        }

        return "Sets and repetitions exercise: [series count: " + CollectionUtils.size(sets)
                + ", prescription details : " + exPrescLoggableString + "]";
    }

    @Override
    public WeightsDTO toDTO() {
        List<SingleSetDTO> sets = CollectionUtils.isNotEmpty(getSets())
                ? getSets().stream().map(SingleSet::toDTO).collect(Collectors.toList())
                : new ArrayList<>();

        return WeightsDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .sets(sets)

                .maxTimeSec(getMaxTimeSec())
                .maxTimeMin(getMaxTimeMin())
                .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDTO() : null)
                .build();
    }
}

