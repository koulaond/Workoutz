package com.ondrejkoula.endpoint.workout;

import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.dto.workout.WorkoutDTO;
import com.ondrejkoula.endpoint.UpdateEndpoint;
import com.ondrejkoula.service.workout.WorkoutService;

public class WorkoutEndpoint extends UpdateEndpoint<Workout, WorkoutDTO, WorkoutService> {

    public WorkoutEndpoint(WorkoutService service) {
        super(service);
    }

    @Override
    protected Workout toDomain(WorkoutDTO dto) {
        return dto.toDomain();
    }

    @Override
    protected WorkoutDTO toDTO(Workout domainEntity) {
        return domainEntity.toDTO();
    }
}
