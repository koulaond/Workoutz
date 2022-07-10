package com.ondrejkoula.endpoint.workout;

import com.ondrejkoula.domain.workout.Workout;
import com.ondrejkoula.dto.workout.WorkoutDTO;
import com.ondrejkoula.endpoint.CrudEndpoint;
import com.ondrejkoula.service.workout.WorkoutService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/workouts")
public class WorkoutEndpoint extends CrudEndpoint<Workout, WorkoutDTO, WorkoutService> {

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
