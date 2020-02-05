package com.ondrejkoula.repository;

import com.ondrejkoula.domain.preset.ExercisePreset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisePresetRepository extends JpaRepository<ExercisePreset, Long> {
}
