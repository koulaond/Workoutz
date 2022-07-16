package com.ondrejkoula.repository.exercise;

import com.ondrejkoula.domain.exercise.weights.Weights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightsRepository extends JpaRepository<Weights, Long> {
}