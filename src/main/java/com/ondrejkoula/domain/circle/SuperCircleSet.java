package com.ondrejkoula.domain.circle;

import com.ondrejkoula.domain.DomainEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "super_circle_sets")
public class SuperCircleSet extends DomainEntity {

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
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
}
