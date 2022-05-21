package com.ondrejkoula.service.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.Circle;
import com.ondrejkoula.repository.exercise.circle.CircleRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.merger.DataMerger;
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
