package com.ondrejkoula.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondrejkoula.domain.exercise.Condition;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConditionDTO extends ExerciseDTO {

    private ExercisePrescriptionDTO exercisePrescription;

    private Integer timeSec;

    private Integer timeMin;

    @Builder
    public ConditionDTO(Long id, String status, String note, ExercisePrescriptionDTO exercisePrescription,
                        Integer timeSec, Integer timeMin) {
        super(id, status, note);
        this.exercisePrescription = exercisePrescription;
        this.timeSec = timeSec;
        this.timeMin = timeMin;
    }

    public static ConditionDTO from(Condition condition) {
        ConditionDTOBuilder builder = ConditionDTO.builder()
                .id(condition.getId())
                .status(condition.getStatus())
                .note(condition.getNote())
                .timeSec(condition.getTimeSec())
                .timeMin(condition.getTimeMin());

        if (condition.getExercisePrescription() != null) {
            builder.exercisePrescription(ExercisePrescriptionDTO.from(condition.getExercisePrescription()));
        }
        return builder.build();
    }

}
