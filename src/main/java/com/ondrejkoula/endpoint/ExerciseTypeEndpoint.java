package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.ExerciseType;
import com.ondrejkoula.dto.ExerciseTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ondrejkoula.service.ExerciseTypeService;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/exerciseTypes")
public class ExerciseTypeEndpoint {

    @Autowired
    private ExerciseTypeService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<ExerciseTypeDto> findAll() {
        List<ExerciseType> all = service.findAll();
        return all.stream()
                .map(exerciseType -> modelMapper.map(exerciseType, ExerciseTypeDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{type}")
    public ResponseEntity<ExerciseTypeDto> findByType(@PathVariable String type) {
        ExerciseType found = service.findByType(type);
        if (found != null) return new ResponseEntity<>(modelMapper.map(found, ExerciseTypeDto.class), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
