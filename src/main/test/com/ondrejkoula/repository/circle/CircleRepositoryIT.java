package com.ondrejkoula.repository.circle;

import com.ondrejkoula.domain.exercise.circle.Circle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CircleRepositoryIT {

    @Autowired
    CircleRepository repository;

    @Test
    void shouldCreateAndDeleteCircleExercise() {

        Circle circle = Circle.builder()
                .id(1L)
                .status("DRAFT")
                .prepareTime(15)
                .build();
        repository.save(circle);

        List<Circle> all = repository.findAll();

        assertThat(all)
                .hasSize(1)
                .satisfies(list -> assertThat(list.get(0))
                        .hasFieldOrPropertyWithValue("status", "DRAFT")
                        .hasFieldOrPropertyWithValue("id", 1L)
                        .hasFieldOrPropertyWithValue("prepareTime", 15));

        repository.deleteById(circle.getId());

        assertThat(repository.findAll()).isEmpty();
    }
}
