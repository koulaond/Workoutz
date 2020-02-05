package com.ondrejkoula.domain.preset;

import com.ondrejkoula.domain.Exercise;
import com.ondrejkoula.domain.converter.DoubleIntArrayConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue(value ="SuperSet")
public class SuperSetPreset extends ExercisePreset {

    @ManyToMany
    private List<Exercise> exercises;

    private Integer series;

    @Convert(converter = DoubleIntArrayConverter.class)
    private List<List<Integer>> repsPerSeries;

}
