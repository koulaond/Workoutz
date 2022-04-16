package com.ondrejkoula.repository.workout;

import com.ondrejkoula.domain.workout.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
