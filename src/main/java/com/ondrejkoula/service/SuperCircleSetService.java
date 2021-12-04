package com.ondrejkoula.service;

import com.ondrejkoula.domain.circle.SuperCircleSet;
import com.ondrejkoula.repository.SuperCircleSetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SuperCircleSetService {

    private final SuperCircleSetRepository repository;

    @Autowired
    public SuperCircleSetService(SuperCircleSetRepository repository) {
        this.repository = repository;
    }

    public SuperCircleSet save(SuperCircleSet superCircleSet) {
        log.info("Saving Super-circle set: {}", superCircleSet);
        SuperCircleSet saved = repository.save(superCircleSet);
        log.info("Super set successfully saved: {}", saved);
        return saved;
    }

    public SuperCircleSet findById(Long id) {
        log.info("Getting Super set with ID:  " + id);
        Optional<SuperCircleSet> found = repository.findById(id);
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
