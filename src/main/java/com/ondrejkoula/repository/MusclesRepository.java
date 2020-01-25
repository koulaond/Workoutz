package com.ondrejkoula.repository;

import com.ondrejkoula.domain.Muscles;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MusclesRepository extends Repository<Muscles, Long> {
    List<Muscles> findAll();

    Muscles findById(Long id);

    Muscles findByName(String name);

    List<Muscles> findByBodyPart(String bodyPart);
}
