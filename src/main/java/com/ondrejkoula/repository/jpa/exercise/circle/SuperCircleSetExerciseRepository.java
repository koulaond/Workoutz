package com.ondrejkoula.repository.jpa.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.repository.jpa.CompositionChildRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperCircleSetExerciseRepository extends CompositionChildRepository<SuperCircleSetExercise> {
}
