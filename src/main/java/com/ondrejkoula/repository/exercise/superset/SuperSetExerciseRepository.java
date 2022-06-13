package com.ondrejkoula.repository.exercise.superset;

import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.repository.CompositionChildRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperSetExerciseRepository extends JpaRepository<SuperSetExercise, Long>, CompositionChildRepository<SuperSetExercise> {

}
