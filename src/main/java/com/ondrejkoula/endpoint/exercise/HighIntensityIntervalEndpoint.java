package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.HighIntensityInterval;
import com.ondrejkoula.dto.exercise.HighIntensityIntervalDTO;
import com.ondrejkoula.endpoint.CrudEndpoint;
import com.ondrejkoula.service.exercise.HighIntensityIntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/hiit")
public class HighIntensityIntervalEndpoint extends CrudEndpoint<HighIntensityInterval, HighIntensityIntervalDTO, HighIntensityIntervalService> {

    @Autowired
    public HighIntensityIntervalEndpoint(HighIntensityIntervalService service) {
        super(service);
    }

    @Override
    protected HighIntensityInterval toDomain(HighIntensityIntervalDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected HighIntensityIntervalDTO toDTO(HighIntensityInterval domainEntity) {
        return domainEntity.toDTO();
    }
}
