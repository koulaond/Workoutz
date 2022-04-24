package com.ondrejkoula.service;


import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.DataChanges;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.service.merger.DataMerger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Collections.singletonMap;

@Slf4j
public class GenericService<DE extends DomainEntity, R extends JpaRepository<DE, Long>> {

    protected final R repository;

    protected final DataMerger dataMerger;

    public GenericService(R repository, DataMerger dataMerger) {
        this.repository = repository;
        this.dataMerger = dataMerger;
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

    public DE create(DE toSave) {
        return repository.save(toSave);
    }

    public DE update(Long id, DataChanges dataChanges) {
        DE foundExistingRecord = repository.findById(id).orElseThrow(()
                -> new DataNotFoundException(format("Item with id: %s not found", id), "notFound"));


        dataMerger.mergeSourceToTarget(dataChanges, foundExistingRecord);
        return repository.save(foundExistingRecord);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
