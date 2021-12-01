package com.ondrejkoula.repository;

import com.ondrejkoula.domain.circle.Circle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CircleRepository extends JpaRepository<Circle, String> {
}
