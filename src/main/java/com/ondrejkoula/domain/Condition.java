package com.ondrejkoula.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "conditions")
public class Condition extends DomainEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    @Column(name = "time_sec")
    private Integer timeSec;

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
}
