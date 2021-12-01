package com.ondrejkoula.repository;

import com.ondrejkoula.domain.superset.SuperSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperSetRepository extends JpaRepository<SuperSet, String> {
}
