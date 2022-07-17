package com.ondrejkoula.repository.jpa.exercise.circle;

import com.ondrejkoula.domain.exercise.circle.Circle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CircleRepository extends JpaRepository<Circle, Long> {
}
