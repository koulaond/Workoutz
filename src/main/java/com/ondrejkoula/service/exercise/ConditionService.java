package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.Condition;
import com.ondrejkoula.repository.jpa.exercise.ConditionRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConditionService extends GenericService<Condition> {

    public ConditionService(ConditionRepository repository) {
        super(repository);
    }
}
