package com.ondrejkoula.service;


import com.ondrejkoula.domain.DomainEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Slf4j
public class GenericService<DE extends DomainEntity, R extends JpaRepository<DE, Long>> {

    protected final R repository;

    public GenericService(R repository) {
        this.repository = repository;
    }

    public DE findById(Long id) {
        log.info("Getting {} with ID: {}", getClass().getSimpleName(), id);
        Optional<DE> found = repository.findById(id);

        if (!found.isPresent()) {
            log.info("{} with ID: {} not exists.", getClass().getSimpleName(), id);
            return null; // TODO throw NotFound exception
        }

        log.info("{} with ID: {} found.", getClass().getSimpleName(), id);
        return found.get();
    }

    public DE save(DE toSave) {
        log.info("Saving {}: {}", getClass().getSimpleName(), toSave.loggableString());
        DE saved = repository.save(toSave);

        log.info("{} successfully saved: {}", getClass().getSimpleName(), saved.loggableString());
        return saved;
    }
}
