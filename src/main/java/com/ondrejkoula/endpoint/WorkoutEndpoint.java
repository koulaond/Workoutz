package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.dto.WorkoutDto;
import com.ondrejkoula.dto.create.WorkoutCreateDto;
import com.ondrejkoula.service.ExercisePresetService;
import com.ondrejkoula.service.WorkoutExerciseUnitService;
import com.ondrejkoula.service.WorkoutService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/workout")
public class WorkoutEndpoint {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private ExercisePresetService exercisePresetService;

    @Autowired
    private WorkoutExerciseUnitService workoutExerciseUnitService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<WorkoutDto> create(@RequestBody WorkoutCreateDto dto) {
        Workout workout = modelMapper.map(dto, Workout.class);
        Workout created = workoutService.create(workout);
        WorkoutDto workoutDto = modelMapper.map(created, WorkoutDto.class);
        return new ResponseEntity<>(workoutDto, HttpStatus.OK);
    }
}
