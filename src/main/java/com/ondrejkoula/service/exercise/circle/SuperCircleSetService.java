package com.ondrejkoula.service.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircleSet;
import com.ondrejkoula.repository.exercise.circle.SuperCircleSetRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperCircleSetService extends GenericService<SuperCircleSet, SuperCircleSetRepository> {

    public SuperCircleSetService(SuperCircleSetRepository repository) {
        super(repository);
    }
}
