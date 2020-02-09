package com.ondrejkoula.service;

import com.ondrejkoula.domain.preset.ExercisePreset;
import com.ondrejkoula.repository.ExercisePresetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExercisePresetService {

    @Autowired
    private ExercisePresetRepository exercisePresetRepository;

    public ExercisePreset save(ExercisePreset exercisePreset) {
        return exercisePresetRepository.save(exercisePreset);
    }

    public List<ExercisePreset> findAll() {
        return exercisePresetRepository.findAll();
    }

    public List<ExercisePreset> findByPresetType(String presetType) {
        return exercisePresetRepository.findByPresetType(presetType);
    }

    public ExercisePreset findById(Long id) {
        return exercisePresetRepository.findById(id).orElseThrow(IllegalStateException::new); // TODO Replace by specific exception
    }

}
