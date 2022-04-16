package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.exercise.SetsAndRepetitions;
import com.ondrejkoula.domain.Status;
import com.ondrejkoula.repository.exercise.SetsAndRepetitionsRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SetsAndRepetitionsService extends GenericService<SetsAndRepetitions, SetsAndRepetitionsRepository> {

    @Autowired
    public SetsAndRepetitionsService(SetsAndRepetitionsRepository repository) {
        super(repository);
    }

    public SetsAndRepetitions markAsReady(Long id) {
        SetsAndRepetitions setsAndRepetitions = findById(id);
        Status status = Status.valueOf(setsAndRepetitions.getStatus());

        if (Status.READY.equals(status)) {
            log.info("Sets and repetitions with ID {} are already set as ready", id);
            return setsAndRepetitions;
        }

        setsAndRepetitions.setStatus(Status.READY.name());
        return save(setsAndRepetitions);
    }

}
