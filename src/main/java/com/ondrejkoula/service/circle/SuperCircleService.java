package com.ondrejkoula.service.circle;

import com.ondrejkoula.domain.circle.SuperCircle;
import com.ondrejkoula.repository.circle.SuperCircleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SuperCircleService {

    private final SuperCircleRepository repository;

    public SuperCircleService(SuperCircleRepository repository) {
        this.repository = repository;
    }

    public SuperCircle save(SuperCircle superCircle) {
        log.info("Saving Super-circle: {}", superCircle);
        SuperCircle saved = repository.save(superCircle);
        log.info("Super-circle successfully saved: {}", saved);
        return saved;
    }

    public SuperCircle findById(Long id) {
        log.info("Getting Super-circle with ID:  " + id);
        Optional<SuperCircle> found = repository.findById(id);
        if (!found.isPresent()) {
            log.info("Super-circle with ID:  " + id + " not exists.");
            return null; // TODO throw NotFound exception
        }
        log.info("Super-circle with ID:  " + id + " found.");
        return found.get();
    }

    public void deleteById(Long id) {
        log.info("Deleting Super-circle with ID:  " + id);
        repository.deleteById(id);
    }
}
