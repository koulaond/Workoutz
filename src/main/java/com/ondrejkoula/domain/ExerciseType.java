package com.ondrejkoula.domain;

import com.ondrejkoula.dto.ExerciseTypeDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "exercise_types")
public class ExerciseType extends DomainEntity {

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;

    @Builder
    public ExerciseType(Long id, String status, String note, String type, String category) {
        super(id, status, note);
        this.type = type;
        this.category = category;
    }

    public static ExerciseType from(ExerciseTypeDTO dto) {
        return ExerciseType.builder()
                .status(dto.getStatus())
                .type(dto.getType())
                .category(dto.getCategory())
                .build();
    }

    @Override
    public String loggableString() {
        return "Exercise type: [type: " + type + ", category: " + category + "]";
    }
}
