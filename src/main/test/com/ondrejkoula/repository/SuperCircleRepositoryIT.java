package com.ondrejkoula.repository;

import com.ondrejkoula.domain.circle.SuperCircle;
import com.ondrejkoula.domain.circle.SuperCircleSet;
import com.ondrejkoula.domain.circle.SuperCircleSetExercise;
import com.ondrejkoula.repository.circle.SuperCircleRepository;
import com.ondrejkoula.repository.circle.SuperCircleSetExerciseRepository;
import com.ondrejkoula.repository.circle.SuperCircleSetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SuperCircleRepositoryIT {

    @Autowired
    SuperCircleRepository superCircleRepository;

    @Autowired
    SuperCircleSetRepository superCircleSetRepository;

    @Autowired
    SuperCircleSetExerciseRepository superCircleSetExerciseRepository;

    @Test
    void shouldCreateSuperSetWithSetsAndExercises() {

        SuperCircleSetExercise setExerciseFirst = SuperCircleSetExercise.builder().timeOverriddenSec(15).build();
        SuperCircleSetExercise exerciseSecond = SuperCircleSetExercise.builder().timeOverriddenSec(25).build();

        SuperCircleSet superCircleSet = SuperCircleSet.builder().status("draft").setExercises(asList(setExerciseFirst, exerciseSecond)).build();

        SuperCircle superCircle = SuperCircle.builder().workTime(45).definedSets(singletonList(superCircleSet)).build();

        superCircleRepository.save(superCircle);

        assertThat(superCircleRepository.findAll())
                .hasSize(1)
                .satisfies(savedCircleSet -> assertThat(savedCircleSet.get(0))
                        .satisfies(savedCircle -> {
                            assertThat(savedCircle.getWorkTime()).isEqualTo(45);
                            assertThat(savedCircle.getDefinedSets())
                                    .hasSize(1)
                                    .satisfies(setsList -> assertThat(setsList.get(0))
                                            .satisfies(savedSet -> {
                                                assertThat(savedSet.getStatus()).isEqualTo("draft");
                                                assertThat(savedSet.getSetExercises())
                                                        .hasSize(2);
                                                assertThat(savedSet.getSetExercises().get(0).getTimeOverriddenSec())
                                                        .isEqualTo(15);
                                                assertThat(savedSet.getSetExercises().get(1).getTimeOverriddenSec())
                                                        .isEqualTo(25);
                                            }));
                        }));
    }
}
