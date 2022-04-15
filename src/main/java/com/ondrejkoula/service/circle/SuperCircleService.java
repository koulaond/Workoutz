package com.ondrejkoula.service.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircle;
import com.ondrejkoula.repository.circle.SuperCircleRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperCircleService extends GenericService<SuperCircle, SuperCircleRepository> {

    public SuperCircleService(SuperCircleRepository repository) {
        super(repository);
    }
}
