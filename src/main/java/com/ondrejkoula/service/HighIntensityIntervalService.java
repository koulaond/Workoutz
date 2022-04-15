package com.ondrejkoula.service;

import com.ondrejkoula.domain.exercise.HighIntensityInterval;
import com.ondrejkoula.repository.HighIntensityIntervalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HighIntensityIntervalService extends GenericService<HighIntensityInterval, HighIntensityIntervalRepository> {

    public HighIntensityIntervalService(HighIntensityIntervalRepository repository) {
        super(repository);
    }
}
