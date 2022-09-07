package com.ondrejkoula.service;

import com.ondrejkoula.PersistenceTest;
import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.exception.PositionOutOfRangeException;
import com.ondrejkoula.exception.validation.ValidationException;
import com.ondrejkoula.repository.jpa.exercise.superset.SuperSetExerciseRepository;
import com.ondrejkoula.repository.jpa.exercise.superset.SuperSetRepository;
import com.ondrejkoula.service.dependencies.exercise.ExerciseDependencyService;
import com.ondrejkoula.service.exercise.superset.SuperSetService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SuperSetExerciseServiceTest extends PersistenceTest {

    @Autowired
    SuperSetExerciseRepository superSetExerciseRepository;

    @Autowired
    SuperSetRepository superSetRepository;
    
    @Autowired
    ExerciseDependencyService exerciseDependencyService;

    SuperSetService service;

    @BeforeEach
    void setup() {
        service = new SuperSetService(superSetExerciseRepository, superSetRepository, exerciseDependencyService);
    }

    @Test
    void insertExerciseToSet_shouldSuccessfullyInsert() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").parent(parentSet).position(0).build());
        SuperSetExercise newOne = SuperSetExercise.builder().note("newOne").position(0).build();
        service.assignNewChildToParent(parentSet.getId(), newOne);
        List<SuperSetExercise> allExercises = service.findChildrenByParent(parentSet.getId());

        assertThat(allExercises).hasSize(2)
                .satisfies(exercises -> {
                    assertThat(exercises.get(0)).satisfies(exercise -> {
                        assertThat(exercise.getPosition()).isEqualTo(0);
                        assertThat(exercise.getNote()).isEqualTo("newOne");
                    });
                    assertThat(exercises.get(1)).satisfies(exercise -> {
                        assertThat(exercise.getPosition()).isEqualTo(1);
                        assertThat(exercise.getNote()).isEqualTo("formerlyFirst");
                    });
                });
    }

    @Test
    void insertExerciseToSet_shouldFailDueParentNotFound() {
        SuperSetExercise newOne = SuperSetExercise.builder().note("newOne").position(0).build();
        assertThrows(ValidationException.class, () -> service.assignNewChildToParent(1L, newOne));
    }

    @Test
    void insertExerciseToSet_shouldFailDueToMissingPosition() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").parent(parentSet).position(0).build());
        SuperSetExercise newOne = SuperSetExercise.builder().note("newOne").build();

        assertThrows(ValidationException.class, () -> service.assignNewChildToParent(1L, newOne));
    }

    @Test
    void insertExerciseToSet_shouldFailDueToPositionOutOfRange() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").parent(parentSet).position(0).build());
        SuperSetExercise newOne = SuperSetExercise.builder().note("newOne").build();

        assertThrows(ValidationException.class, () -> service.assignNewChildToParent(2L, newOne));
    }

    @Test
    void changeExercisePosition_exerciseMovedUp() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());

        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").parent(parentSet).position(0).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlySecond").parent(parentSet).position(1).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyThird").parent(parentSet).position(2).build());
        SuperSetExercise formerlyFourth = superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFourth").parent(parentSet).position(3).build());

        service.changeChildPosition(formerlyFourth.getId(), 0);

        List<SuperSetExercise> allExercises = superSetExerciseRepository.findByParentIdOrderByPosition(parentSet.getId());
        Assertions.assertThat(allExercises).hasSize(4);
        Assertions.assertThat(allExercises.get(0).getNote()).isEqualTo("formerlyFourth");
        Assertions.assertThat(allExercises.get(1).getNote()).isEqualTo("formerlyFirst");
        Assertions.assertThat(allExercises.get(2).getNote()).isEqualTo("formerlySecond");
        Assertions.assertThat(allExercises.get(3).getNote()).isEqualTo("formerlyThird");
    }

    @Test
    void changeExercisePosition_exerciseMovedDown() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());

        SuperSetExercise formerlyFirst = superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").parent(parentSet).position(0).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlySecond").parent(parentSet).position(1).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyThird").parent(parentSet).position(2).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFourth").parent(parentSet).position(3).build());

        service.changeChildPosition(formerlyFirst.getId(), 3);

        List<SuperSetExercise> allExercises = superSetExerciseRepository.findByParentIdOrderByPosition(parentSet.getId());
        Assertions.assertThat(allExercises).hasSize(4);
        Assertions.assertThat(allExercises.get(3).getNote()).isEqualTo("formerlyFirst");
        Assertions.assertThat(allExercises.get(0).getNote()).isEqualTo("formerlySecond");
        Assertions.assertThat(allExercises.get(1).getNote()).isEqualTo("formerlyThird");
        Assertions.assertThat(allExercises.get(2).getNote()).isEqualTo("formerlyFourth");
    }


    @Test
    void changeExercisePosition_positionIsOutOfRange() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());

        SuperSetExercise formerlyFirst = superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").parent(parentSet).position(0).build());

        assertThrows(PositionOutOfRangeException.class,
                () -> service.changeChildPosition(formerlyFirst.getId(), 3));

    }


    @Test
    void changeExercisePosition_positionIsNegative() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());

        SuperSetExercise formerlyFirst = superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").parent(parentSet).position(0).build());

        assertThrows(PositionOutOfRangeException.class,
                () -> service.changeChildPosition(formerlyFirst.getId(), -1));

    }


    @Test
    void removeExerciseFromSet() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());

        SuperSetExercise formerlyFirst = superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").parent(parentSet).position(0).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlySecond").parent(parentSet).position(1).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyThird").parent(parentSet).position(2).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFourth").parent(parentSet).position(3).build());

        service.removeExistingChildFromParent(formerlyFirst.getId());

        List<SuperSetExercise> allExercises = superSetExerciseRepository.findByParentIdOrderByPosition(parentSet.getId());

        Assertions.assertThat(allExercises).hasSize(3);
        Assertions.assertThat(allExercises.get(0).getNote()).isEqualTo("formerlySecond");
        Assertions.assertThat(allExercises.get(1).getNote()).isEqualTo("formerlyThird");
        Assertions.assertThat(allExercises.get(2).getNote()).isEqualTo("formerlyFourth");
    }

}