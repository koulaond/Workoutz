package com.ondrejkoula.endpoint;


import com.ondrejkoula.domain.TrainingPlan;
import com.ondrejkoula.dto.TrainingPlanDto;
import com.ondrejkoula.dto.TrainingPlanWorkoutDto;
import com.ondrejkoula.service.TrainingPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/trainingPlan")
public class TrainingPlanEndpoint {

    @Autowired
    private TrainingPlanService service;

    @Autowired
    private ModelMapper modelMapper;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TrainingPlanDto> create(@RequestBody TrainingPlanDto dto) {
        if (dto.getId() != null) { // null ID when exists so that service will always create new training plan
            dto.setId(null);
        }
        TrainingPlan trainingPlan = modelMapper.map(dto, TrainingPlan.class);
        TrainingPlan created = service.save(trainingPlan);
        return new ResponseEntity<>(modelMapper.map(created, TrainingPlanDto.class), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<TrainingPlanDto> findAll() {
        return service.findAll()
                .stream()
                .map(trainingPlan -> modelMapper.map(trainingPlan, TrainingPlanDto.class))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/byId/{id}")
    public ResponseEntity<TrainingPlanDto> findById(@PathVariable Long id) {
        TrainingPlan found = service.findById(id);
        return new ResponseEntity<>(modelMapper.map(found, TrainingPlanDto.class), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/mountWorkout")
    public ResponseEntity<TrainingPlanDto> mountWorkoutToPlan(@RequestBody TrainingPlanWorkoutDto dto) {
        TrainingPlan trainingPlan = service.addWorkoutToTrainingPlan(
                dto.getTrainingPlanId(),
                dto.getWorkoutId(),
                dto.getWeek(),
                dto.getDayInWeek(),
                dto.getOrderWithinDay(),
                dto.getPhase());
        return new ResponseEntity<>(modelMapper.map(trainingPlan, TrainingPlanDto.class), HttpStatus.OK);
    }
}
