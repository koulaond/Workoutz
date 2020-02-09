package com.ondrejkoula.repository;

import com.ondrejkoula.domain.WorkoutExerciseUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutExerciseUnitRepository extends JpaRepository<WorkoutExerciseUnit, Long> {

   // @Query("SELECT weu FROM WorkoutExerciseUnit weu WHERE weu.workoutId = ?1 ORDER BY weu.position ASC")
    List<WorkoutExerciseUnit> findByIdWorkoutIdOrderByIdPosition(Long workoutId);
}
