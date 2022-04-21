package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.ExerciseType;
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
    public ExerciseTypeDTO create(@RequestBody ExerciseTypeDTO exerciseType) {
        ExerciseType toCreate = ExerciseType.from(exerciseType);

        ExerciseType saved = exerciseTypeService.create(toCreate);
        return ExerciseTypeDTO.from(saved);
    }
}
