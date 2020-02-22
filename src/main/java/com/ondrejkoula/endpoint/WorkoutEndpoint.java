package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.TrainingPlanWorkout;
import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.dto.TrainingPlanWorkoutDto;
import com.ondrejkoula.dto.WorkoutDto;
import com.ondrejkoula.dto.create.WorkoutCreateDto;
import com.ondrejkoula.dto.create.WorkoutUpdateDto;
import com.ondrejkoula.service.WorkoutService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/workout")
public class WorkoutEndpoint {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<WorkoutDto> create(@RequestBody WorkoutCreateDto dto) {
        Workout workout = modelMapper.map(dto, Workout.class);
        Workout created = workoutService.create(workout);
        WorkoutDto workoutDto = modelMapper.map(created, WorkoutDto.class);
        return new ResponseEntity<>(workoutDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<WorkoutDto> update(@RequestBody WorkoutUpdateDto dto) {
        if (dto.getId() == null) throw new IllegalStateException("Missing ID"); // TODO  replace by  specific exception and handler
        Workout workout = modelMapper.map(dto, Workout.class);
        Workout updated = workoutService.update(workout);
        WorkoutDto workoutDto = modelMapper.map(updated, WorkoutDto.class);
        return new ResponseEntity<>(workoutDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<WorkoutDto>> findAll() {
        List<Workout> allWorkouts = workoutService.findAll();
        List<WorkoutDto> dtos = allWorkouts.stream()
                .map(workout -> modelMapper.map(workout, WorkoutDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "byTrainingPlan/{trainingPlanId}")
    public ResponseEntity<List<TrainingPlanWorkoutDto>> findWorkoutsForTrainingPlan(@PathVariable Long trainingPlanId) {
        List<TrainingPlanWorkout> workoutsForTrainingPlan = workoutService.findWorkoutsForTrainingPlan(trainingPlanId);
        List<TrainingPlanWorkoutDto> dtos = workoutsForTrainingPlan.stream()
                .map(trainingPlanWorkout -> modelMapper.map(trainingPlanWorkout, TrainingPlanWorkoutDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<WorkoutDto> findById(@PathVariable Long id) {
        Workout created = workoutService.findById(id);
        WorkoutDto workoutDto = modelMapper.map(created, WorkoutDto.class);
        return new ResponseEntity<>(workoutDto, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(Long id) {
        workoutService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
