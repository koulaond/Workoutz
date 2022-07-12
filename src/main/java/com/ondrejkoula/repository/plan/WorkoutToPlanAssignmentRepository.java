package com.ondrejkoula.repository.plan;

import com.ondrejkoula.domain.plan.WorkoutToPlanAssignment;
import com.ondrejkoula.domain.plan.WorkoutToPlanAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutToPlanAssignmentRepository extends JpaRepository<WorkoutToPlanAssignment, WorkoutToPlanAssignmentId> {

    @Query("select w from WorkoutToPlanAssignment w where w.pk.plan.id = :planId order by w.dateAndTimeScheduled")
    List<WorkoutToPlanAssignment> getWorkoutsAssignedToPlan(@Param("planId") Long planId);


}
