package com.ondrejkoula.service.superset;

import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.repository.superset.SuperSetExerciseRepository;
import com.ondrejkoula.repository.superset.SuperSetRepository;
import com.ondrejkoula.service.IncorporatedItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperSetExerciseService extends IncorporatedItemService<SuperSetExercise, SuperSetExerciseRepository, SuperSet, SuperSetRepository> {

    @Autowired
    public SuperSetExerciseService(SuperSetExerciseRepository repository,
                                   SuperSetRepository superSetRepository) {
        super(repository, superSetRepository);
    }
}
