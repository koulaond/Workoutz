package com.ondrejkoula.repository.jpa.exercise.superset;

import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.repository.jpa.CompositionChildRepository;

import java.util.List;

public interface SuperSetExerciseRepository extends CompositionChildRepository<SuperSetExercise> {

    List<SuperSetExercise> findByExercisePrescriptionId(Long exercisePrescriptionId);
}
