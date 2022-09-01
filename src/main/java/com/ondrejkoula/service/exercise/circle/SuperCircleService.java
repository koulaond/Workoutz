package com.ondrejkoula.service.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircle;
import com.ondrejkoula.repository.jpa.exercise.circle.SuperCircleRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.dependencies.NoDependenciesCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperCircleService extends GenericService<SuperCircle> {

    public SuperCircleService(SuperCircleRepository repository, NoDependenciesCollector dependenciesCollector) {
        super(repository, dependenciesCollector);
    }
}
