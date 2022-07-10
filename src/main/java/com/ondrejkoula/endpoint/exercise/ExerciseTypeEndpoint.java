package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.endpoint.CrudEndpoint;
import com.ondrejkoula.service.exercise.ExerciseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/types")
public class ExerciseTypeEndpoint extends CrudEndpoint<ExerciseType, ExerciseTypeDTO, ExerciseTypeService> {

    @Autowired
    public ExerciseTypeEndpoint(ExerciseTypeService service) {
        super(service);
    }

    @Override
    protected ExerciseType toDomain(ExerciseTypeDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected ExerciseTypeDTO toDTO(ExerciseType domainEntity) {
        return domainEntity.toDTO();
    }

}
