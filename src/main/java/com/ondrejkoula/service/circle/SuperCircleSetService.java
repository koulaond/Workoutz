package com.ondrejkoula.service.circle;

import com.ondrejkoula.domain.circle.SuperCircleSet;
import com.ondrejkoula.repository.circle.SuperCircleSetRepository;
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
