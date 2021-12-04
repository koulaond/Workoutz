package com.ondrejkoula.service;

import com.ondrejkoula.domain.Condition;
import com.ondrejkoula.repository.ConditionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ConditionService {
    
    private final ConditionRepository repository;

    @Autowired
    public ConditionService(ConditionRepository repository) {
        this.repository = repository;
    }

    public Condition save(Condition condition) {
        log.info("Saving Condition: {}", condition);
        Condition saved = repository.save(condition);
        log.info("Condition successfully saved: {}", saved);
        return saved;
    }

    public Condition findById(Long id) {
        log.info("Getting Condition with ID:  " + id);
        Optional<Condition> found = repository.findById(id);
        if (!found.isPresent()) {
            log.info("Condition with ID:  " + id + " not exists.");
            return null; // TODO throw NotFound exception
        }
        log.info("Condition with ID:  " + id + " found.");
        return found.get();
    }

    public void deleteById(Long id) {
        log.info("Deleting Condition with ID:  " + id);
        repository.deleteById(id);
    }
}
