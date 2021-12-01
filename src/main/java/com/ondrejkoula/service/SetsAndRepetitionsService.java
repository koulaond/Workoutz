package com.ondrejkoula.service;

import com.ondrejkoula.domain.SetsAndRepetitions;
import com.ondrejkoula.domain.Status;
import com.ondrejkoula.repository.SetsAndRepetitionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SetsAndRepetitionsService {

    private final SetsAndRepetitionsRepository repository;

    @Autowired
    public SetsAndRepetitionsService(SetsAndRepetitionsRepository repository) {
        this.repository = repository;
    }

    public SetsAndRepetitions save(SetsAndRepetitions setsAndRepetitions) {
        log.info("Saving Sets and Repetitions exercise.");
        return repository.save(setsAndRepetitions);
    }

    public SetsAndRepetitions findById(String id) {
        log.info("Getting Sets and Repetitions exercise with ID:  " + id);
        Optional<SetsAndRepetitions> found = repository.findById(id);
        if (!found.isPresent()) {
            log.info("Sets and Repetitions exercise with ID:  " + id + " not exists.");
            return null; // TODO throw NotFound exception
        }
        log.info("Sets and Repetitions exercise with ID:  " + id + " found.");
        return found.get();
    }

    public void deleteById(String id) {
        log.info("Deleting Sets and Repetitions exercise with ID:  " + id);
        repository.deleteById(id);
    }

    public SetsAndRepetitions markAsReady(String id) {
        SetsAndRepetitions setsAndRepetitions = findById(id);
        Status status = setsAndRepetitions.getStatus();
        if (Status.READY.equals(status)) {
            log.info("Sets and repetitions with ID {} are already set as ready", id);
            return setsAndRepetitions;
        }
        setsAndRepetitions.setStatus(Status.READY);
        return save(setsAndRepetitions);
    }
}
