package com.ondrejkoula.repository.circle;

import com.ondrejkoula.domain.exercise.circle.SuperCircle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperCircleRepository extends JpaRepository<SuperCircle, Long> {
}
