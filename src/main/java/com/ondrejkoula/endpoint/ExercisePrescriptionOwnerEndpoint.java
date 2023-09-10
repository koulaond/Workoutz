package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.exercise.ExercisePrescriptionOwner;
import com.ondrejkoula.dto.AbstractDTO;
import com.ondrejkoula.dto.ChangeExercisePrescriptionDTO;
import com.ondrejkoula.service.exercise.ExercisePrescriptionOwnerService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class ExercisePrescriptionOwnerEndpoint<DE extends ExercisePrescriptionOwner, DTO extends AbstractDTO,
        S extends ExercisePrescriptionOwnerService<DE>> extends CrudEndpoint<DE, DTO, S>{
    
    public ExercisePrescriptionOwnerEndpoint(S service) {
        super(service);
    }

    @PutMapping(value = "/changePrescription", produces = "application/json")
    public DTO changeExercisePrescription(@RequestBody ChangeExercisePrescriptionDTO dto) {
        DE updatedParent = service.changeExercisePrescription(dto.getParentId(), dto.getExercisePrescriptionId());
        return toDTO(updatedParent);
    }
}
