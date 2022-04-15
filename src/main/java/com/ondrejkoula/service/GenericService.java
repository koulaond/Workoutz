package com.ondrejkoula.service;


import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.exception.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonMap;

@Slf4j
public class GenericService<DE extends DomainEntity, R extends JpaRepository<DE, Long>> {

    protected final R repository;

    public GenericService(R repository) {
        this.repository = repository;
    }

    public DE findById(Long id) {
        Optional<DE> found = repository.findById(id);
        return found.orElseThrow(() -> new DataNotFoundException("Data not found", "notFound", singletonMap("id", id.toString())));
    }

    public List<DE> findAll() {
        return repository.findAll();
    }

    public List<DE> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Page<DE> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public DE save(DE toSave) {
        return repository.save(toSave);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
