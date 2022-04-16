package com.ondrejkoula.repository.exercise.circle;

import com.ondrejkoula.repository.IncorporatedItemRepository;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperCircleSetExerciseRepository extends JpaRepository<SuperCircleSetExercise, Long>,
        IncorporatedItemRepository<SuperCircleSetExercise> {
}
