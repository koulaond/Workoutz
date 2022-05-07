package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import com.ondrejkoula.endpoint.GenericEndpoint;
import com.ondrejkoula.service.exercise.ExercisePrescriptionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/prescription")
public class ExercisePrescriptionEndpoint extends GenericEndpoint<ExercisePrescription, ExercisePrescriptionDTO, ExercisePrescriptionService> {

    public ExercisePrescriptionEndpoint(ExercisePrescriptionService service) {
        super(service);
    }

    @Override
    protected ExercisePrescription toDomain(ExercisePrescriptionDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected ExercisePrescriptionDTO toDTO(ExercisePrescription domainEntity) {
        return domainEntity.toDTO();
    }

}
