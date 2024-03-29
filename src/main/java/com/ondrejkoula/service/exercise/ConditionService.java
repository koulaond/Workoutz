package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.Condition;
import com.ondrejkoula.repository.jpa.exercise.ConditionRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.dependencies.exercise.ExerciseDependencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConditionService extends GenericService<Condition> {

    public ConditionService(ConditionRepository repository, ExerciseDependencyService dependencyService) {
        super(repository, dependencyService);
    }
}
