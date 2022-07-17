package com.ondrejkoula.repository.jpa.workout;

import com.ondrejkoula.domain.workout.ExerciseToWorkoutAssignment;
import com.ondrejkoula.domain.workout.WorkoutExerciseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutExerciseRepository extends JpaRepository<ExerciseToWorkoutAssignment, WorkoutExerciseId> {

    @Query("select w from ExerciseToWorkoutAssignment w where w.pk.exercise.id = :exerciseId order by w.position")
    List<ExerciseToWorkoutAssignment> getWorkoutsForExercise(@Param("exerciseId") Long exerciseId);

    @Query("select w from ExerciseToWorkoutAssignment w where w.pk.workout.id = :workoutId order by w.position")
    List<ExerciseToWorkoutAssignment> getExercisesForWorkout(@Param("workoutId") Long workoutId);


    @Query("select w from ExerciseToWorkoutAssignment w where w.pk.workout.id = :workoutId and w.position >= :positionAfter order by w.position")
    List<ExerciseToWorkoutAssignment> getExercisesForWorkoutAfterPositionIncluding(@Param("workoutId") Long workoutId,
                                                                                   @Param("positionAfter") Integer positionAfter);

}
