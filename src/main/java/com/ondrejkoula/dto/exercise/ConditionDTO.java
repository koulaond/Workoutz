package com.ondrejkoula.dto.exercise;

import com.ondrejkoula.domain.exercise.Condition;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
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

    @Override
    public Condition toDomain() {
        return Condition.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .timeSec(getTimeSec())
                .timeMin(getTimeMin())
                .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDomain() : null)
                .build();
    }
}
