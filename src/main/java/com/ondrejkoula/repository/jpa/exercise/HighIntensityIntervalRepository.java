package com.ondrejkoula.repository.jpa.exercise;

import com.ondrejkoula.domain.exercise.HighIntensityInterval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HighIntensityIntervalRepository extends JpaRepository<HighIntensityInterval, Long> {

    List<HighIntensityInterval> findByExercisePrescriptionId(Long exercisePrescriptionId);
}
