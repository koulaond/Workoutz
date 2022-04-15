package com.ondrejkoula.repository.circle;

import com.ondrejkoula.IncorporatedItemRepository;
import com.ondrejkoula.domain.circle.SuperCircleSetExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperCircleSetExerciseRepository extends JpaRepository<SuperCircleSetExercise, Long>,
        IncorporatedItemRepository<SuperCircleSetExercise> {
}
