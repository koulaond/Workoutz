package com.ondrejkoula.endpoint.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircle;
import com.ondrejkoula.dto.exercise.circle.SuperCircleDTO;
import com.ondrejkoula.endpoint.CrudEndpoint;
import com.ondrejkoula.service.exercise.circle.SuperCircleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/supercircle")
public class SuperCircleEndpoint extends CrudEndpoint<SuperCircle, SuperCircleDTO, SuperCircleService> {

    public SuperCircleEndpoint(SuperCircleService service) {
        super(service);
    }

    @Override
    protected SuperCircle toDomain(SuperCircleDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected SuperCircleDTO toDTO(SuperCircle domainEntity) {
        return domainEntity.toDTO();
    }
}
