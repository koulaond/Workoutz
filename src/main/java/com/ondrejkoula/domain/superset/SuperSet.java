package com.ondrejkoula.domain.superset;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.ExerciseValue;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class SuperSet extends DomainEntity {

    private ExerciseValue<Integer> seriesCount;

    private List<SuperSetExercise> seriesContent;

}
