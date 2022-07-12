package com.ondrejkoula.endpoint.plan;

import com.ondrejkoula.domain.plan.WorkoutToPlanAssignment;
import com.ondrejkoula.dto.plan.AssignWorkoutToPlanDTO;
import com.ondrejkoula.dto.plan.WorkoutToPlanAssignmentDTO;
import com.ondrejkoula.service.plan.WorkoutToPlanAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/assignment/plans")
public class WorkoutToPlanAssignmentEndpoint {

    private final WorkoutToPlanAssignmentService service;

    @Autowired
    public WorkoutToPlanAssignmentEndpoint(WorkoutToPlanAssignmentService service) {
        this.service = service;
    }

    @PostMapping
    List<WorkoutToPlanAssignmentDTO> assignWorkoutToPlan(@RequestBody AssignWorkoutToPlanDTO dto) {
        service.assignWorkoutToPlan(dto.getWorkoutId(), dto.getPlanId(), dto.getDateAndTimeScheduled());

        List<WorkoutToPlanAssignment> assignedWorkoutsToPlan = service.getAssignedWorkoutsToPlan(dto.getPlanId());
        return assignedWorkoutsToPlan.stream().map(WorkoutToPlanAssignment::toDTO).collect(Collectors.toList());
    }


}
