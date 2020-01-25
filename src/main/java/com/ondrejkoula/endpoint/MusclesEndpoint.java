package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.Muscles;
import com.ondrejkoula.dto.MusclesDto;
import com.ondrejkoula.service.MusclesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestMapping(value = "/muscles")
public class MusclesEndpoint {

    @Autowired
    private MusclesService musclesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<MusclesDto> findAll() {
        List<Muscles> all = musclesService.findAll();
        return all.stream()
                .map(muscles -> modelMapper.map(muscles, MusclesDto.class))
                .collect(toList());
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<MusclesDto> findByName(@PathVariable String name) {
        Muscles byName = musclesService.findByName(name);
        if (byName != null) return new ResponseEntity<>(modelMapper.map(byName, MusclesDto.class), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/bodyPart/{bodyPart}")
    @ResponseBody
    public List<MusclesDto> findByBodyPart(@PathVariable String bodyPart) {
        List<Muscles> byBodyPart = musclesService.findByBodyPart(bodyPart);
        return byBodyPart.stream()
                .map(muscles -> modelMapper.map(muscles, MusclesDto.class))
                .collect(toList());
    }
}
