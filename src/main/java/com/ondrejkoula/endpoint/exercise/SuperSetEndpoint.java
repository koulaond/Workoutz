package com.ondrejkoula.endpoint.exercise;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.dto.datachange.composition.AddNewChildCompositionChange;
import com.ondrejkoula.dto.exercise.superset.SuperSetDTO;
import com.ondrejkoula.dto.exercise.superset.SuperSetExerciseDTO;
import com.ondrejkoula.endpoint.CompositionEndpoint;
import com.ondrejkoula.service.exercise.superset.SuperSetExerciseService;
import com.ondrejkoula.service.exercise.superset.SuperSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/superset")
public class SuperSetEndpoint extends CompositionEndpoint<SuperSet, SuperSetDTO, SuperSetExercise, SuperSetExerciseDTO> {

    @Autowired
    public SuperSetEndpoint(SuperSetService superSetService, SuperSetExerciseService exerciseService) {
        super(superSetService, exerciseService);
    }

    @Override
    protected AddNewChildCompositionChange<SuperSetExerciseDTO> getValueForCreate(ObjectMapper objectMapper, JsonNode value) {
        return null;
    }

    @Override
    protected SuperSetExercise convertChildDtoToDomain(SuperSetExerciseDTO childDto) {
        return childDto.toDomain();
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
