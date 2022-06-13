package com.ondrejkoula.repository.exercise.circle;

import com.ondrejkoula.repository.CompositionChildRepository;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperCircleSetExerciseRepository extends JpaRepository<SuperCircleSetExercise, Long>,
        CompositionChildRepository<SuperCircleSetExercise> {
}
