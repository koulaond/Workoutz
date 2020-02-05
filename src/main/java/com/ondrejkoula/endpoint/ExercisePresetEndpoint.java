package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.preset.ExercisePreset;
import com.ondrejkoula.dto.preset.ExercisePresetDto;
import com.ondrejkoula.service.ExercisePresetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/exercisePreset")
public class ExercisePresetEndpoint {

    @Autowired
    private ExercisePresetService exercisePresetService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public <T extends ExercisePresetDto> ResponseEntity<ExercisePresetDto> create(@RequestBody T dto) {
        ExercisePreset preset = modelMapper.map(dto, ExercisePreset.class);
        ExercisePreset created = exercisePresetService.create(preset);
        ExercisePresetDto createdDto = modelMapper.map(created, ExercisePresetDto.class);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ExercisePresetDto> findAll() {
        return null;
    }
}
