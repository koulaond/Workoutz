package com.ondrejkoula.service;

import com.ondrejkoula.domain.HighIntensityInterval;
import com.ondrejkoula.repository.HighIntensityIntervalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class HighIntensityIntervalService {

    private final HighIntensityIntervalRepository repository;

    public HighIntensityIntervalService(HighIntensityIntervalRepository repository) {
        this.repository = repository;
    }

    public HighIntensityInterval save(HighIntensityInterval highIntensityInterval) {
        log.info("Saving High-intensity interval: {}", highIntensityInterval);
        HighIntensityInterval saved = repository.save(highIntensityInterval);
        log.info("High-intensity interval successfully saved: {}", saved);
        return saved;
    }

    public HighIntensityInterval findById(Long id) {
        log.info("Getting High-intensity interval with ID:  " + id);
        Optional<HighIntensityInterval> found = repository.findById(id);
        if (!found.isPresent()) {
            log.info("High-intensity interval with ID:  " + id + " not exists.");
            return null; // TODO throw NotFound exception
        }
        log.info("High-intensity interval with ID:  " + id + " found.");
        return found.get();
    }

    public void deleteById(Long id) {
        log.info("Deleting High-intensity interval with ID:  " + id);
        repository.deleteById(id);
    }
}
