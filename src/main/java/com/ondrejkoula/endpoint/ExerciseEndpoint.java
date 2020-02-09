package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.Exercise;
import com.ondrejkoula.domain.ExerciseType;
import com.ondrejkoula.domain.Muscles;
import com.ondrejkoula.dto.ExerciseCreateDto;
import com.ondrejkoula.dto.ExerciseDto;
import com.ondrejkoula.service.ExerciseService;
import com.ondrejkoula.service.ExerciseTypeService;
import com.ondrejkoula.service.MusclesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestMapping("/exercise")
public class ExerciseEndpoint {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private MusclesService musclesService;

    @Autowired
    private ExerciseTypeService exerciseTypeService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseCreateDto exerciseCreateDto) {
        Long typeId = exerciseCreateDto.getTypeId();
        ExerciseType exerciseType = exerciseTypeService.findById(typeId);
        List<Muscles> muscles = exerciseCreateDto.getMuscleIds()
                .stream()
                .map(muscleId -> musclesService.findById(muscleId))
                .collect(toList());
        Exercise exercise = new Exercise(exerciseCreateDto.getName(), exerciseCreateDto.getDescription(), muscles, exerciseType);
        Exercise created = exerciseService.save(exercise);
        return new ResponseEntity<>(mapToDto(created), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/findByName")
    public ResponseEntity<List<ExerciseDto>> findByName(@RequestParam String name) {
        List<ExerciseDto> collect = exerciseService.findByName(name)
                .stream()
                .map(this::mapToDto)
                .collect(toList());
        if (collect.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(collect, HttpStatus.OK);
    }

    private ExerciseDto mapToDto(Exercise exercise) {
        ExerciseDto exerciseDto = modelMapper.map(exercise, ExerciseDto.class);
        exerciseDto.setMuscles(exercise.getMuscles().stream().map(Muscles::getName).collect(toList()));
        exerciseDto.setType(exercise.getExerciseType().getType());
        return exerciseDto;
    }
}
