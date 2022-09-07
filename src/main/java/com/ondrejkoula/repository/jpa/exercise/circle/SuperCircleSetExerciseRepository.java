package com.ondrejkoula.repository.jpa.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import com.ondrejkoula.repository.jpa.CompositionChildRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperCircleSetExerciseRepository extends CompositionChildRepository<SuperCircleSetExercise> {

    List<SuperCircleSetExercise> findByExercisePrescriptionId(Long exercisePrescriptionId);
    
    
}
