package com.ondrejkoula.domain.exercise;

import com.ondrejkoula.dto.exercise.ConditionDTO;
import com.ondrejkoula.service.validation.annotation.Required;
import com.ondrejkoula.service.validation.annotation.RequiredReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "conditions")
public class Condition extends Exercise {

    @RequiredReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    @Required
    @Column(name = "time_sec")
    private Integer timeSec;

    @Required
    @Column(name = "time_min")
    private Integer timeMin;

    @Builder
    public Condition(Long id, String status, String note, ExercisePrescription exercisePrescription, Integer timeSec, Integer timeMin) {
        super(id, status, note);
        this.exercisePrescription = exercisePrescription;
        this.timeSec = timeSec;
        this.timeMin = timeMin;
    }

    @Override
    public String loggableString() {

        String exPrescLoggableString = "null";
        if (!Objects.isNull(exercisePrescription)) {
            exPrescLoggableString = exercisePrescription.loggableString();
        }
        return "Condition exercise: [minutes: "+ timeMin + ", seconds: "
                + timeSec + ", prescription details: " + exPrescLoggableString + "]";
    }

    @Override
    public ConditionDTO toDTO() {
        return ConditionDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .timeMin(getTimeMin())
                .timeSec(getTimeSec())
                .exercisePrescription(getExercisePrescription() != null ? getExercisePrescription().toDTO() : null)
                .build();
    }
}
