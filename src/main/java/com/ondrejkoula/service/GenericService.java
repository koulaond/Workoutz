package com.ondrejkoula.service;


import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.DataChanges;
import com.ondrejkoula.exception.CascadeDependenciesException;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.service.merger.DataMerger;
import com.ondrejkoula.service.validation.DataValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Collections.singletonMap;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.collections4.MapUtils.isNotEmpty;

@Slf4j
public abstract class GenericService<DE extends DomainEntity, R extends JpaRepository<DE, Long>> {

    protected final R repository;

    protected final DataMerger dataMerger;

    protected final DataValidator dataValidator;

    public GenericService(R repository) {
        this.repository = repository;
        this.dataMerger = new DataMerger();
        this.dataValidator = new DataValidator();
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
        dataValidator.validateAllMandatoryDataPresent(toSave);
        return repository.save(toSave);
    }

    public DE update(Long id, DataChanges dataChanges) {
        DE foundExistingRecord = repository.findById(id).orElseThrow(()
                -> new DataNotFoundException(format("Item with id: %s not found", id), "notFound"));

        dataMerger.mergeSourceToTarget(dataChanges, foundExistingRecord);
        dataValidator.validateAllMandatoryDataPresent(foundExistingRecord);
        return repository.save(foundExistingRecord);
    }

    public void deleteById(Long id) {
        Map<String, List<? extends DomainEntity>> allDependencies = findAllDependencies(id);

        if (isNotEmpty(allDependencies)) {
            Map<String, Integer> occurrences = allDependencies.entrySet().stream()
                    .collect(toMap(Map.Entry::getKey, entry -> entry.getValue().size()));

            throw new CascadeDependenciesException(id, occurrences);
        }
        repository.deleteById(id);
    }

    public Map<String, List<? extends DomainEntity>> findAllDependencies(Long id) {
        Map<String, List<? extends DomainEntity>> allDependencies = new HashMap<>();
        doFindAllDependencies(id, allDependencies);
        return allDependencies;
    }

    protected void doFindAllDependencies(Long id, Map<String, List<? extends DomainEntity>> allDependencies) {

    }

}
