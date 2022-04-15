package com.ondrejkoula.repository.superset;

import com.ondrejkoula.domain.exercise.superset.SuperSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperSetRepository extends JpaRepository<SuperSet, Long> {
}
