package com.ondrejkoula.repository.jpa.exercise.circle;

import com.ondrejkoula.PersistenceTest;
import com.ondrejkoula.domain.exercise.circle.SuperCircle;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSet;
import com.ondrejkoula.domain.exercise.circle.SuperCircleSetExercise;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class SuperCircleSetAndExercisesRepositoryTest extends PersistenceTest {

    @Autowired
    SuperCircleRepository superCircleRepository;

    @Autowired
    SuperCircleSetRepository setRepository;

    @Autowired
    SuperCircleSetExerciseRepository exerciseRepository;

    /**
     * Create super circle with sets and exercises.
     */
    @Test
    void shouldCreateAndDeleteSuperCircleCompleteStructure() {
        SuperCircleSetExercise exercise = SuperCircleSetExercise.builder().build();
        SuperCircleSet set = SuperCircleSet.builder().setExercises(new ArrayList<>(singletonList(exercise))).build();
        SuperCircle superCircle = SuperCircle.builder().set(set).build();

        superCircle = superCircleRepository.save(superCircle);

        assertThat(superCircleRepository.findAll()).hasSize(1);
        assertThat(setRepository.findAll()).hasSize(1);
        assertThat(exerciseRepository.findAll()).hasSize(1);

        superCircleRepository.delete(superCircle);

        assertThat(superCircleRepository.findAll()).hasSize(0);
        assertThat(setRepository.findAll()).hasSize(0);
        assertThat(exerciseRepository.findAll()).hasSize(0);

    }
}
