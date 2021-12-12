package com.ondrejkoula.service.circle;

import com.ondrejkoula.domain.circle.Circle;
import com.ondrejkoula.repository.circle.CircleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class CircleService {

    private final CircleRepository repository;

    @Autowired
    public CircleService(CircleRepository repository) {
        this.repository = repository;
    }

    public Circle save(Circle circle) {
        log.info("Saving Circle: {}", circle);
        Circle saved = repository.save(circle);
        log.info("Circle successfully saved: {}", saved);
        return saved;
    }

    public Circle findById(Long id) {
        log.info("Getting Circle with ID:  " + id);
        Optional<Circle> found = repository.findById(id);
        if (!found.isPresent()) {
            log.info("Circle with ID:  " + id + " not exists.");
            return null; // TODO throw NotFound exception
        }
        log.info("Circle with ID:  " + id + " found.");
        return found.get();
    }

    public void deleteById(Long id) {
        log.info("Deleting Circle with ID:  " + id);
        repository.deleteById(id);
    }
}
