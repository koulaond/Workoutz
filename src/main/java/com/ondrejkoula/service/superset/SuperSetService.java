package com.ondrejkoula.service.superset;

import com.ondrejkoula.domain.superset.SuperSet;
import com.ondrejkoula.repository.superset.SuperSetRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperSetService extends GenericService<SuperSet, SuperSetRepository> {

    @Autowired
    public SuperSetService(SuperSetRepository repository) {
       super(repository);
    }
}
