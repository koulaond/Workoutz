package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.dto.exercise.superset.SuperSetDTO;
import com.ondrejkoula.endpoint.CrudEndpoint;
import com.ondrejkoula.service.exercise.superset.SuperSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/superset")
public class SuperSetEndpoint extends CrudEndpoint<SuperSet, SuperSetDTO, SuperSetService> {

    @Autowired
    public SuperSetEndpoint(SuperSetService superSetService) {
        super(superSetService);
    }

    @Override
    public SuperSetDTO create(SuperSetDTO dto) {
        return super.create(dto);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    protected SuperSet toDomain(SuperSetDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected SuperSetDTO toDTO(SuperSet domainEntity) {
        return domainEntity.toDTO();
    }

}
