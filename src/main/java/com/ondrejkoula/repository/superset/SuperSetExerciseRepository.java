package com.ondrejkoula.repository.superset;

import com.ondrejkoula.domain.superset.SuperSetExercise;
import com.ondrejkoula.IncorporatedItemRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperSetExerciseRepository extends JpaRepository<SuperSetExercise, Long>, IncorporatedItemRepository<SuperSetExercise> {

}
