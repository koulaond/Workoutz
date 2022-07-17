package com.ondrejkoula.repository.jpa.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperCircleRepository extends JpaRepository<SuperCircle, Long> {
}
