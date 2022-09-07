package com.ondrejkoula.service.dependencies.workout;

import com.ondrejkoula.domain.EntityType;
import com.ondrejkoula.domain.plan.Plan;
import com.ondrejkoula.domain.plan.WorkoutToPlanAssignment;
import com.ondrejkoula.dto.Dependencies;
import com.ondrejkoula.repository.jpa.plan.WorkoutToPlanAssignmentRepository;
import com.ondrejkoula.service.dependencies.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class WorkoutDependencyService extends DependencyService {
    
    private final WorkoutToPlanAssignmentRepository repository;

    @Autowired
    public WorkoutDependencyService(WorkoutToPlanAssignmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doCollect(Long workoutId, List<Dependencies> dependenciesList) {
        List<WorkoutToPlanAssignment> assignmentList = repository.getPlansContainingWorkout(workoutId);
        List<Plan> plansContainingWorkout = assignmentList.stream().map(assignment -> assignment.getPk().getPlan()).collect(toList());
        registerDependenciesForEntityType(EntityType.PLAN, plansContainingWorkout, dependenciesList);
    }
}
