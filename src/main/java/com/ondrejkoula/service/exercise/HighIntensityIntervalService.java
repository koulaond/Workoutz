package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.HighIntensityInterval;
import com.ondrejkoula.repository.jpa.exercise.HighIntensityIntervalRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.dependencies.NoDependenciesCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HighIntensityIntervalService extends GenericService<HighIntensityInterval> {

    public HighIntensityIntervalService(HighIntensityIntervalRepository repository, NoDependenciesCollector dependenciesCollector) {
        super(repository, dependenciesCollector);
    }
}
