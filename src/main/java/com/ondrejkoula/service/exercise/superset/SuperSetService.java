package com.ondrejkoula.service.exercise.superset;

import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.repository.exercise.superset.SuperSetRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.merger.DataMerger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperSetService extends GenericService<SuperSet, SuperSetRepository> {

    @Autowired
    public SuperSetService(SuperSetRepository repository, DataMerger dataMerger) {
       super(repository, dataMerger);
    }
}
