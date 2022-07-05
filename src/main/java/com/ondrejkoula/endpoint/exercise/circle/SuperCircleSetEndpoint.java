package com.ondrejkoula.endpoint.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircleSet;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.dto.exercise.circle.SuperCircleSetDTO;
import com.ondrejkoula.dto.exercise.circle.SuperCircleSetExerciseDTO;
import com.ondrejkoula.endpoint.CompositionEndpoint;
import com.ondrejkoula.service.CompositionService;
import com.ondrejkoula.service.GenericService;
import com.ondrejkoula.service.exercise.circle.SuperCircleSetExerciseService;
import com.ondrejkoula.service.exercise.circle.SuperCircleSetService;


public class SuperCircleSetEndpoint extends CompositionEndpoint<SuperCircleSet, SuperCircleSetDTO, SuperCircleSetExercise, SuperCircleSetExerciseDTO> {

    public SuperCircleSetEndpoint(SuperCircleSetExerciseService childService, SuperCircleSetService parentService) {
        super(childService, parentService);
    }

    @Override
    protected SuperCircleSet toDomain(SuperCircleSetDTO dto) {
        return null;
    }

    @Override
    protected SuperCircleSetDTO toDTO(SuperCircleSet domainEntity) {
        return null;
    }

    @Override
    protected Class<SuperCircleSetExerciseDTO> getDtoClass() {
        return null;
    }

    @Override
    protected SuperCircleSetExercise convertChildDtoToDomain(SuperCircleSetExerciseDTO childDto) {
        return null;
    }
}
