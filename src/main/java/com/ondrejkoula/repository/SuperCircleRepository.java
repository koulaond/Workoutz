package com.ondrejkoula.repository;

import com.ondrejkoula.domain.circle.SuperCircle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperCircleRepository extends JpaRepository<SuperCircle, String> {
}
