package com.ondrejkoula;

import com.ondrejkoula.domain.TrainingPlan;
import com.ondrejkoula.domain.Workout;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class TrainingPlanWorkoutID implements Serializable {


}
