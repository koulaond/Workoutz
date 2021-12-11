package com.ondrejkoula.service;

import com.ondrejkoula.domain.superset.SuperSet;
import com.ondrejkoula.domain.superset.SuperSetExercise;
import com.ondrejkoula.dto.SuperSetExerciseChangePositionDTO;
import com.ondrejkoula.dto.SuperSetExerciseDTO;
import com.ondrejkoula.exception.ValidationException;
import com.ondrejkoula.repository.superset.SuperSetExerciseRepository;
import com.ondrejkoula.repository.superset.SuperSetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SuperSetExerciseServiceTest {

    @Autowired
    SuperSetExerciseRepository superSetExerciseRepository;

    @Autowired
    SuperSetRepository superSetRepository;

    SuperSetExerciseService service;

    @BeforeEach
    void setup() {
        service = new SuperSetExerciseService(superSetExerciseRepository, superSetRepository);
    }

    @Test
    void findById() {
    }

    @Test
    void findByParentSetId() {
    }

    @Test
    void insertExerciseToSet_shouldSuccessfullyInsert() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").superSet(parentSet).position(0).build());
        SuperSetExerciseDTO newOne = SuperSetExerciseDTO.builder().note("newOne").position(0).build();
        service.insertExerciseToSet(parentSet.getId(), newOne);
        List<SuperSetExercise> allExercises = service.findByParentSetId(parentSet.getId());

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
        SuperSetExerciseDTO newOne = SuperSetExerciseDTO.builder().note("newOne").position(0).build();
        assertThrows(ValidationException.class, () -> service.insertExerciseToSet(1L, newOne));
    }

    @Test
    void insertExerciseToSet_shouldFailDueToMissingPosition() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").superSet(parentSet).position(0).build());
        SuperSetExerciseDTO newOne = SuperSetExerciseDTO.builder().note("newOne").build();

        assertThrows(ValidationException.class, () -> service.insertExerciseToSet(1L, newOne));
    }

    @Test
    void insertExerciseToSet_shouldFailDueToPositionOutOfRange() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").superSet(parentSet).position(0).build());
        SuperSetExerciseDTO newOne = SuperSetExerciseDTO.builder().note("newOne").build();

        assertThrows(ValidationException.class, () -> service.insertExerciseToSet(2L, newOne));
    }

    @Test
    void changeExercisePosition_exerciseMovedUp() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());

        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").superSet(parentSet).position(0).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlySecond").superSet(parentSet).position(1).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyThird").superSet(parentSet).position(2).build());
        SuperSetExercise formerlyFourth = superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFourth").superSet(parentSet).position(3).build());

        service.changeExercisePosition(
                SuperSetExerciseChangePositionDTO.builder()
                        .id(formerlyFourth.getId())
                        .newPosition(0)
                        .build()
        );

        List<SuperSetExercise> allExercises = superSetExerciseRepository.findBySuperSetId(parentSet.getId());
        Assertions.assertThat(allExercises).hasSize(4);
        Assertions.assertThat(allExercises.get(0).getNote()).isEqualTo("formerlyFourth");
        Assertions.assertThat(allExercises.get(1).getNote()).isEqualTo("formerlyFirst");
        Assertions.assertThat(allExercises.get(2).getNote()).isEqualTo("formerlySecond");
        Assertions.assertThat(allExercises.get(3).getNote()).isEqualTo("formerlyThird");
    }

    @Test
    void changeExercisePosition_exerciseMovedDown() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());

        SuperSetExercise formerlyFirst = superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").superSet(parentSet).position(0).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlySecond").superSet(parentSet).position(1).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyThird").superSet(parentSet).position(2).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFourth").superSet(parentSet).position(3).build());

        service.changeExercisePosition(
                SuperSetExerciseChangePositionDTO.builder()
                        .id(formerlyFirst.getId())
                        .newPosition(3)
                        .build()
        );

        List<SuperSetExercise> allExercises = superSetExerciseRepository.findBySuperSetId(parentSet.getId());
        Assertions.assertThat(allExercises).hasSize(4);
        Assertions.assertThat(allExercises.get(3).getNote()).isEqualTo("formerlyFirst");
        Assertions.assertThat(allExercises.get(0).getNote()).isEqualTo("formerlySecond");
        Assertions.assertThat(allExercises.get(1).getNote()).isEqualTo("formerlyThird");
        Assertions.assertThat(allExercises.get(2).getNote()).isEqualTo("formerlyFourth");
    }


    @Test
    void changeExercisePosition_positionIsOutOfRange() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());

        SuperSetExercise formerlyFirst = superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").superSet(parentSet).position(0).build());

        assertThrows(
                ValidationException.class, () ->
                        service.changeExercisePosition(
                                SuperSetExerciseChangePositionDTO.builder()
                                        .id(formerlyFirst.getId())
                                        .newPosition(3)
                                        .build()
                        ));

    }


    @Test
    void changeExercisePosition_positionIsNegative() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());

        SuperSetExercise formerlyFirst = superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").superSet(parentSet).position(0).build());

        assertThrows(
                ValidationException.class, () ->
                        service.changeExercisePosition(
                                SuperSetExerciseChangePositionDTO.builder()
                                        .id(formerlyFirst.getId())
                                        .newPosition(-1)
                                        .build()
                        ));

    }


    @Test
    void removeExerciseFromSet() {
        SuperSet parentSet = superSetRepository.save(SuperSet.builder().id(42L).build());

        SuperSetExercise formerlyFirst = superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFirst").superSet(parentSet).position(0).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlySecond").superSet(parentSet).position(1).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyThird").superSet(parentSet).position(2).build());
        superSetExerciseRepository.save(SuperSetExercise.builder().note("formerlyFourth").superSet(parentSet).position(3).build());

        service.removeExerciseFromSet(formerlyFirst.getId());

        List<SuperSetExercise> allExercises = superSetExerciseRepository.findBySuperSetId(parentSet.getId());

        Assertions.assertThat(allExercises).hasSize(3);
        Assertions.assertThat(allExercises.get(0).getNote()).isEqualTo("formerlySecond");
        Assertions.assertThat(allExercises.get(1).getNote()).isEqualTo("formerlyThird");
        Assertions.assertThat(allExercises.get(2).getNote()).isEqualTo("formerlyFourth");
    }

}