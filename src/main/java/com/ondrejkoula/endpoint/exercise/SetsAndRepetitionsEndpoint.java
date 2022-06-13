package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.SetsAndRepetitions;
import com.ondrejkoula.dto.exercise.SetsAndRepetitionsDTO;
import com.ondrejkoula.endpoint.CrudEndpoint;
import com.ondrejkoula.service.exercise.SetsAndRepetitionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/repetitions")
public class SetsAndRepetitionsEndpoint extends CrudEndpoint<SetsAndRepetitions, SetsAndRepetitionsDTO, SetsAndRepetitionsService> {

    @Autowired
    public SetsAndRepetitionsEndpoint(SetsAndRepetitionsService service) {
        super(service);
    }

    @Override
    protected SetsAndRepetitions toDomain(SetsAndRepetitionsDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected SetsAndRepetitionsDTO toDTO(SetsAndRepetitions domainEntity) {
        return domainEntity.toDTO();
    }
}
