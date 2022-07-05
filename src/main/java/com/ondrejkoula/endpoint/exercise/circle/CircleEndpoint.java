package com.ondrejkoula.endpoint.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.Circle;
import com.ondrejkoula.dto.exercise.circle.CircleDTO;
import com.ondrejkoula.endpoint.UpdateEndpoint;
import com.ondrejkoula.service.exercise.circle.CircleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/circle")
public class CircleEndpoint extends UpdateEndpoint<Circle, CircleDTO, CircleService> {

    public CircleEndpoint(CircleService service) {
        super(service);
    }

    @Override
    protected Circle toDomain(CircleDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected CircleDTO toDTO(Circle domainEntity) {
        return domainEntity.toDTO();
    }
}
