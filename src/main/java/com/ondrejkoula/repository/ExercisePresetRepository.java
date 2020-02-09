package com.ondrejkoula.repository;

import com.ondrejkoula.domain.preset.ExercisePreset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExercisePresetRepository extends JpaRepository<ExercisePreset, Long> {

    List<ExercisePreset> findByPresetType(String presetType);
}
