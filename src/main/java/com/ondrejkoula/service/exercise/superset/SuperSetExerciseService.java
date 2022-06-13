package com.ondrejkoula.service.exercise.superset;

import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.repository.exercise.superset.SuperSetExerciseRepository;
import com.ondrejkoula.repository.exercise.superset.SuperSetRepository;
import com.ondrejkoula.service.CompositionChildService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperSetExerciseService extends CompositionChildService<SuperSetExercise, SuperSetExerciseRepository, SuperSet, SuperSetRepository> {

    @Autowired
    public SuperSetExerciseService(SuperSetExerciseRepository repository,
                                   SuperSetRepository superSetRepository) {
        super(repository, superSetRepository);
    }
}
