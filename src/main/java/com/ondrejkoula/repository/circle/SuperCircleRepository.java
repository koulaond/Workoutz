package com.ondrejkoula.repository.circle;

import com.ondrejkoula.domain.circle.SuperCircle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperCircleRepository extends JpaRepository<SuperCircle, Long> {
}
