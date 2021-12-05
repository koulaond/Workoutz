package com.ondrejkoula.repository.superset;

import com.ondrejkoula.domain.superset.SuperSetExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuperSetExerciseRepository extends JpaRepository<SuperSetExercise, Long> {

    List<SuperSetExercise> findSuperSetExercisesBySuperSetIdOrderByOrderInSet(Long superSetId);
}
