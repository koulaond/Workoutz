package com.ondrejkoula.service;

import com.ondrejkoula.domain.superset.SuperSet;
import com.ondrejkoula.repository.superset.SuperSetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SuperSetService {

    private final SuperSetRepository repository;

    @Autowired
    public SuperSetService(SuperSetRepository repository) {
        this.repository = repository;
    }

    public SuperSet save(SuperSet superSet) {
        log.info("Saving Super set: {}", superSet);
        SuperSet saved = repository.save(superSet);
        log.info("Super set successfully saved: {}", saved);
        return saved;
    }

    public SuperSet findById(Long id) {
        log.info("Getting Super set with ID:  " + id);
        Optional<SuperSet> found = repository.findById(id);
        if (!found.isPresent()) {
            log.info("Super set with ID:  " + id + " not exists.");
            return null; // TODO throw NotFound exception
        }
        log.info("Super set with ID:  " + id + " found.");
        return found.get();
    }

    public void deleteById(Long id) {
        log.info("Deleting Super set with ID:  " + id);
        repository.deleteById(id);
    }
}
