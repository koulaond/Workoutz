package com.ondrejkoula.domain;

import com.ondrejkoula.dto.ExerciseTypeDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise_types")
public class ExerciseType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;

    public static ExerciseType from(ExerciseTypeDTO dto) {
        return ExerciseType.builder()
                .status(dto.getStatus())
                .type(dto.getType())
                .category(dto.getCategory())
                .build();
    }
}
