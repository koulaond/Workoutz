package com.ondrejkoula.endpoint.exercise;

import com.ondrejkoula.service.exercise.ExercisePrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercises/prescription")
public class ExercisePrescriptionEndpoint {

    private final ExercisePrescriptionService exercisePrescriptionService;

    @Autowired
    public ExercisePrescriptionEndpoint(ExercisePrescriptionService exercisePrescriptionService) {
        this.exercisePrescriptionService = exercisePrescriptionService;
    }
}
