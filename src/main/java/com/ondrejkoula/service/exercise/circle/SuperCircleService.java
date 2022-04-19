package com.ondrejkoula.service.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircle;
import com.ondrejkoula.repository.exercise.circle.SuperCircleRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.merger.DataMerger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuperCircleService extends GenericService<SuperCircle, SuperCircleRepository> {

    public SuperCircleService(SuperCircleRepository repository, DataMerger dataMerger) {
        super(repository, dataMerger);
    }
}
