package com.ondrejkoula.repository.workout;

import com.ondrejkoula.domain.workout.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

    @Query("select w from WorkoutExercise w where w.pk.exercise.id = :exerciseId order by w.position")
    List<WorkoutExercise> getWorkoutsForExercise(@Param("exerciseId") Long exerciseId);

    @Query("select w from WorkoutExercise w where w.pk.workout.id = :workoutId order by w.position")
    List<WorkoutExercise> getExercisesForWorkout(@Param("workoutId") Long workoutId);


    @Query("select w from WorkoutExercise w where w.pk.workout.id = :workoutId and w.position >= :positionAfter order by w.position")
    List<WorkoutExercise> getExercisesForWorkoutAfterPosition(@Param("workoutId") Long workoutId,
                                                              @Param("positionAfter") Integer positionAfter);

}
