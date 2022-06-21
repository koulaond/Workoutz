package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.dto.exercise.ExercisePrescriptionDTO;
import com.ondrejkoula.endpoint.UpdateEndpoint;
import com.ondrejkoula.service.exercise.ExercisePrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/prescription")
public class ExercisePrescriptionEndpoint extends UpdateEndpoint<ExercisePrescription, ExercisePrescriptionDTO, ExercisePrescriptionService> {

    @Autowired
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
