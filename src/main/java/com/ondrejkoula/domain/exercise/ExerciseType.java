package com.ondrejkoula.domain.exercise;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.service.validation.annotation.Required;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "exercise_types")
public class ExerciseType extends DomainEntity {

    @Required
    @Column(name = "type")
    private String value;

    @Required
    @Column(name = "category")
    private String category;

    @Builder
    public ExerciseType(Long id, String status, String note, String value, String category) {
        super(id, status, note);
        this.value = value;
        this.category = category;
    }

    @Override
    public String loggableString() {
        return "Exercise type: [value: " + value + ", category: " + category + "]";
    }

    @Override
    public ExerciseTypeDTO toDTO() {
        return ExerciseTypeDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .value(getValue())
                .category(getCategory())
                .build();
    }
}
