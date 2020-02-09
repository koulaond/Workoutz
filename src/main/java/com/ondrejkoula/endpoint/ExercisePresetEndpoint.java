package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.preset.ExercisePreset;
import com.ondrejkoula.dto.preset.ExercisePresetDto;
import com.ondrejkoula.service.ExercisePresetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/exercisePreset")
public class ExercisePresetEndpoint {

    @Autowired
    private ExercisePresetService exercisePresetService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public <T extends ExercisePresetDto> ResponseEntity<ExercisePresetDto> createOrUpdate(@RequestBody T dto) {
        ExercisePreset preset = modelMapper.map(dto, ExercisePreset.class);
        ExercisePreset created = exercisePresetService.save(preset);
        ExercisePresetDto createdDto = modelMapper.map(created, ExercisePresetDto.class);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ExercisePresetDto>> findAll() {
        List<ExercisePreset> found = exercisePresetService.findAll();
        return new ResponseEntity<>(found.stream()
                .map(exercisePreset -> modelMapper.map(exercisePreset, ExercisePresetDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "presetType/{presetType}")
    public ResponseEntity<List<ExercisePresetDto>> findByPresetType(@PathVariable String presetType) {
        List<ExercisePreset> found = exercisePresetService.findByPresetType(presetType);
        return new ResponseEntity<>(found.stream()
                .map(exercisePreset -> modelMapper.map(exercisePreset, ExercisePresetDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
