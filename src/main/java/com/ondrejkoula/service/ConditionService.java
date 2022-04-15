package com.ondrejkoula.service;

import com.ondrejkoula.domain.Condition;
import com.ondrejkoula.repository.ConditionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConditionService extends GenericService<Condition, ConditionRepository>{

    public ConditionService(ConditionRepository repository) {
        super(repository);
    }
}
