package com.ondrejkoula.service.exercise.superset;

import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.repository.jpa.exercise.superset.SuperSetExerciseRepository;
import com.ondrejkoula.repository.jpa.exercise.superset.SuperSetRepository;
import com.ondrejkoula.service.CompositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperSetService extends CompositionService<SuperSetExercise, SuperSet> {

    @Autowired
    public SuperSetService(SuperSetExerciseRepository superSetExerciseRepository, SuperSetRepository superSetRepository) {
       super(superSetExerciseRepository, superSetRepository);
    }
}
