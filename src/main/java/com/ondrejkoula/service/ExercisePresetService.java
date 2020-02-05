package com.ondrejkoula.service;

import com.ondrejkoula.domain.preset.ExercisePreset;
import com.ondrejkoula.repository.ExercisePresetRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ExercisePresetService {

    @Autowired
    private ExercisePresetRepository exercisePresetRepository;

    public ExercisePreset create(ExercisePreset exercisePreset) {
        return exercisePresetRepository.save(exercisePreset);
    }
}
