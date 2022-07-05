package com.ondrejkoula.repository.exercise;

import com.ondrejkoula.PersistenceTest;
import com.ondrejkoula.domain.exercise.superset.SuperSet;
import com.ondrejkoula.domain.exercise.superset.SuperSetExercise;
import com.ondrejkoula.repository.exercise.superset.SuperSetExerciseRepository;
import com.ondrejkoula.repository.exercise.superset.SuperSetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class SuperSetAndExerciseRepositoryIT extends PersistenceTest {

    @Autowired
    SuperSetRepository superSetRepository;

    @Autowired
    SuperSetExerciseRepository exerciseRepository;

    @Test
    void shouldDeleteChildAfterParentDelete() {
        exerciseRepository.save(SuperSetExercise.builder().position(0).build());
        exerciseRepository.save(SuperSetExercise.builder().position(1).build());

        List<SuperSetExercise> allExercises = exerciseRepository.findAll();
        assertThat(allExercises).hasSize(2); // Exercises should be persisted

        SuperSet savedSet = superSetRepository.save(
                SuperSet.builder()
                        .seriesCount(3)
                        .seriesContent(allExercises)
                        .build()
        );
        List<SuperSet> allSets = superSetRepository.findAll();
        assertThat(allSets).hasSize(1);

        allExercises = exerciseRepository.findAll();
        assertThat(allExercises).hasSize(2); // There should be still 2 exercises

        superSetRepository.delete(savedSet);
        allExercises = exerciseRepository.findAll();
        assertThat(allExercises).hasSize(0); // No exercise left after parent set was deleted
    }

    @Test
    void shouldDeleteOrphanAfterParentDelete() {
        SuperSetExercise exercise = exerciseRepository.save(SuperSetExercise.builder().maxTimeMin(10).build());

        SuperSet savedSet = superSetRepository.save(
                SuperSet.builder()
                        .seriesCount(3)
                        .seriesContent(new ArrayList<>(singletonList(exercise)))
                        .build()
        );
        assertThat(exerciseRepository.findAll()).hasSize(1);

        exercise = exerciseRepository.save(SuperSetExercise.builder().maxTimeMin(20).build());
        savedSet.setSeriesContent(new ArrayList<>(singletonList(exercise)));

        superSetRepository.save(savedSet);
        assertThat(exerciseRepository.findAll()).hasSize(1);
    }

    @Test
    void shouldChangeExercisesOrder() {
        exerciseRepository.save(SuperSetExercise.builder().maxTimeSec(10).position(1).build());
        exerciseRepository.save(SuperSetExercise.builder().maxTimeSec(20).position(2).build());
        exerciseRepository.save(SuperSetExercise.builder().maxTimeSec(30).position(3).build());

        List<SuperSetExercise> allExercises = exerciseRepository.findAll();
        assertThat(allExercises).hasSize(3); // Exercises should be persisted
        assertThat(allExercises.get(0).getPosition()).isEqualTo(1);
        assertThat(allExercises.get(1).getPosition()).isEqualTo(2);
        assertThat(allExercises.get(2).getPosition()).isEqualTo(3);

        SuperSet savedSet = superSetRepository.save(
                SuperSet.builder()
                        .seriesCount(3)
                        .seriesContent(allExercises)
                        .build()
        );

        // Change an order of the first two exercises
        allExercises.get(0).setPosition(2);
        allExercises.get(1).setPosition(1);

        // Set first two exercises, exclude the third one
        savedSet.setSeriesContent(new ArrayList<>(Arrays.asList(allExercises.get(0), allExercises.get(1))));

        superSetRepository.save(savedSet);

        allExercises = exerciseRepository.findAll();

        // Exercises should be only 2, the order should be switched
        assertThat(allExercises).hasSize(2);
        assertThat(allExercises.get(0).getPosition()).isEqualTo(2);
        assertThat(allExercises.get(1).getPosition()).isEqualTo(1);

    }
}
