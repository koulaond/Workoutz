package com.ondrejkoula.service;

import com.ondrejkoula.domain.Muscles;
import com.ondrejkoula.repository.MusclesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MusclesService {

    @Autowired
    private MusclesRepository repository;

    public List<Muscles> findAll() {
        return repository.findAll();
    }

    public Muscles findById(Long id) {
        return repository.findById(id);
    }

    public Muscles findByName(String name) {
        return repository.findByName(name);
    }

    public List<Muscles> findByBodyPart(String bodyPart) {
        return repository.findByBodyPart(bodyPart);
    }
}
