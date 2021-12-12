package com.ondrejkoula.service.circle;

import com.ondrejkoula.domain.circle.SuperCircleSetExercise;
import com.ondrejkoula.repository.circle.SuperCircleSetExerciseRepository;
import com.ondrejkoula.repository.circle.SuperCircleSetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SuperCircleSetExerciseService {
    
    private final SuperCircleSetExerciseRepository repository;

    private final SuperCircleSetRepository circleSetRepository;

    @Autowired
    public SuperCircleSetExerciseService(SuperCircleSetExerciseRepository repository,
                                         SuperCircleSetRepository circleSetRepository) {
        this.repository = repository;
        this.circleSetRepository = circleSetRepository;
    }

    public SuperCircleSetExercise findById(Long id) {
        log.info("Getting Super-circle set exercise with ID:  " + id);
        Optional<SuperCircleSetExercise> found = repository.findById(id);
        if (!found.isPresent()) {
            log.info("Super-circle set exercise with ID:  " + id + " not exists.");
            return null; // TODO throw NotFound exception
        }
        log.info("Super-circle set exercise with ID:  " + id + " found.");
        return found.get();
    }

    public void deleteById(Long id) {
        log.info("Deleting Super-circle set exercise with ID:  " + id);
        repository.deleteById(id);
    }
}
