package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.domain.exercise.ExerciseType;
import com.ondrejkoula.dto.exercise.ExerciseTypeDTO;
import com.ondrejkoula.service.exercise.ExerciseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/types")
public class ExerciseTypeEndpoint {

    private final ExerciseTypeService exerciseTypeService;

    @Autowired
    public ExerciseTypeEndpoint(ExerciseTypeService exerciseTypeService) {
        this.exerciseTypeService = exerciseTypeService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ExerciseTypeDTO create(@RequestBody ExerciseTypeDTO exerciseType) {
        ExerciseType toCreate = ExerciseType.from(exerciseType);

        ExerciseType saved = exerciseTypeService.create(toCreate);
        return ExerciseTypeDTO.from(saved);
    }
}
