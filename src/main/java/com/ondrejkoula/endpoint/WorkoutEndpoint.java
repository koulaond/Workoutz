package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.Workout;
import com.ondrejkoula.domain.WorkoutExerciseUnit;
import com.ondrejkoula.domain.preset.ExercisePreset;
import com.ondrejkoula.dto.WorkoutDto;
import com.ondrejkoula.dto.create.WorkoutCreateDto;
import com.ondrejkoula.dto.create.WorkoutExerciseUnitDto;
import com.ondrejkoula.dto.preset.ExercisePresetDto;
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

import java.util.List;
import java.util.stream.Collectors;

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
        Workout workout = Workout.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .note(dto.getNote())
                .build();
        Workout created = workoutService.create(workout);

        List<WorkoutExerciseUnit> units = dto.getExerciseUnits()
                .stream()
                .map(unitDto -> {
                    ExercisePreset exercisePreset = exercisePresetService.findById(unitDto.getExercisePresetId());
                    return WorkoutExerciseUnit.builder()
                            .exercisePreset(exercisePreset)
                            .id(WorkoutExerciseUnit.WorkoutExerciseUnitId.builder()
                                    .position(unitDto.getPosition())
                                    .workout(workout)
                                    .build())
                            .build();
                })
                .collect(Collectors.toList());
        List<WorkoutExerciseUnit> createdUnits = workoutExerciseUnitService.saveAll(units);
       WorkoutDto workoutDto = WorkoutDto.builder()
               .name(created.getName())
               .description(created.getDescription())
               .note(created.getNote())
               .exerciseUnits(createdUnits.stream()
                       .map(createdUnit-> {
                           ExercisePreset exercisePreset = createdUnit.getExercisePreset();
                           ExercisePresetDto exercisePresetDto = modelMapper.map(exercisePreset, ExercisePresetDto.class);
                           WorkoutExerciseUnitDto unitDto = new WorkoutExerciseUnitDto();
                           unitDto.setPosition(createdUnit.getId().getPosition());
                           unitDto.setWorkoutId(createdUnit.getId().getWorkout().getId());
                           unitDto.setExercisePresetDto(exercisePresetDto);
                           return unitDto;
                       })
                       .collect(Collectors.toList())
               )
               .build();
       return new ResponseEntity<>(workoutDto, HttpStatus.OK);
    }
}
