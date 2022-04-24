package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.dto.DataChanges;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.service.exercise.ExerciseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exercises/types")
public class ExerciseTypeEndpoint {

    private final ExerciseTypeService exerciseTypeService;

    @Autowired
    public ExerciseTypeEndpoint(ExerciseTypeService exerciseTypeService) {
        this.exerciseTypeService = exerciseTypeService;
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ExerciseTypeDTO create(@RequestBody ExerciseTypeDTO dto) {
        ExerciseType toCreate = ExerciseType.from(dto);

        ExerciseType saved = exerciseTypeService.create(toCreate);
        return ExerciseTypeDTO.from(saved);
    }

    @PutMapping(value = "/{id}/update", produces = "application/json")
    public ExerciseTypeDTO update(@PathVariable("id") Long id, @RequestBody DataChanges dataChanges) {


        ExerciseType updated = exerciseTypeService.update(id, dataChanges);
        return ExerciseTypeDTO.from(updated);
    }
}
