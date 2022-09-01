package com.ondrejkoula.service.exercise.superset;

import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.repository.jpa.exercise.superset.SuperSetExerciseRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.dependencies.NoDependenciesCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperSetExerciseService extends GenericService<SuperSetExercise> {

    @Autowired
    public SuperSetExerciseService(SuperSetExerciseRepository repository, NoDependenciesCollector dependenciesCollector) {
        super(repository, dependenciesCollector);
    }
}
