package com.ondrejkoula.repository;

import com.ondrejkoula.domain.exercise.SetsAndRepetitions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetsAndRepetitionsRepository extends JpaRepository<SetsAndRepetitions, Long> {
}
