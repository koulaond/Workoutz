package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.Condition;
import com.ondrejkoula.repository.exercise.ConditionRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.merger.DataMerger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConditionService extends GenericService<Condition, ConditionRepository> {

    public ConditionService(ConditionRepository repository, DataMerger dataMerger) {
        super(repository, dataMerger, dataValidator);
    }
}
