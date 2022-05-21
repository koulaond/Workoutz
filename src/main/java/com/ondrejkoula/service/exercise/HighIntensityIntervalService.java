package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.HighIntensityInterval;
import com.ondrejkoula.repository.exercise.HighIntensityIntervalRepository;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.merger.DataMerger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HighIntensityIntervalService extends GenericService<HighIntensityInterval, HighIntensityIntervalRepository> {

    public HighIntensityIntervalService(HighIntensityIntervalRepository repository) {
        super(repository);
    }
}
