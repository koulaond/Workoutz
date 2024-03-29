package com.ondrejkoula.domain.exercise.circle;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.exercise.circle.SuperCircleSetDTO;
import com.ondrejkoula.dto.exercise.circle.SuperCircleSetExerciseDTO;
import com.ondrejkoula.service.validation.annotation.Required;
import com.ondrejkoula.service.validation.annotation.RequiredReferences;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Data
@NoArgsConstructor
@Entity
@Table(name = "super_circle_sets")
public class SuperCircleSet extends DomainEntity {

    @RequiredReferences
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuperCircleSetExercise> setExercises;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_circle_id")
    private SuperCircle superCircle;

    @Builder
    public SuperCircleSet(Long id, String status, String note, List<SuperCircleSetExercise> setExercises, SuperCircle superCircle) {
        super(id, status, note);
        this.setExercises = setExercises;
        this.superCircle = superCircle;
    }

    @Override
    public String loggableString() {
        String excsLoggableStrings = "null";
        if (CollectionUtils.isNotEmpty(setExercises)) {
            excsLoggableStrings = setExercises.stream()
                    .map(SuperCircleSetExercise::loggableString)
                    .collect(joining(";"));
        }
        return "Super circle set: [exercises: " + excsLoggableStrings + "]";
    }

    @Override
    public SuperCircleSetDTO toDTO() {
        List<SuperCircleSetExerciseDTO> setExerciseDTOs = CollectionUtils.isNotEmpty(setExercises)
                ? setExercises.stream().map(SuperCircleSetExercise::toDTO).collect(Collectors.toList())
                : new ArrayList<>();

        return SuperCircleSetDTO.builder()
                .id(getId())
                .status(getStatus())
                .note(getNote())
                .setExercises(setExerciseDTOs)
                .build();
    }
}
