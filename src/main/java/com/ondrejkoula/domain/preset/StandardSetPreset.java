package com.ondrejkoula.domain.preset;

import com.ondrejkoula.domain.Exercise;
import com.ondrejkoula.domain.converter.SingleIntArrayConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue(value ="StandardSet")
public class StandardSetPreset extends ExercisePreset {

    @ManyToOne
    private Exercise exercise;

    private Integer series;

    @Convert(converter = SingleIntArrayConverter.class)
    private List<Integer> repsPerSeries;
}
