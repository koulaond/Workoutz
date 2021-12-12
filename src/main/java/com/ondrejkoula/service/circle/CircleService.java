package com.ondrejkoula.service.circle;

import com.ondrejkoula.domain.circle.Circle;
import com.ondrejkoula.repository.circle.CircleRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CircleService extends GenericService<Circle, CircleRepository> {

    @Autowired
    public CircleService(CircleRepository repository) {
        super(repository);
    }

}
