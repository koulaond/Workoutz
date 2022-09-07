package com.ondrejkoula.service;


import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.Dependencies;
import com.ondrejkoula.dto.datachange.DataChanges;
import com.ondrejkoula.exception.CascadeDependenciesException;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.service.dependencies.DependencyService;
import com.ondrejkoula.service.merger.ColumnFieldDataMerger;
import com.ondrejkoula.service.validation.DataValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Collections.singletonMap;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Slf4j
public abstract class GenericService<DE extends DomainEntity> {

    protected final JpaRepository<DE, Long> repository;

    protected final ColumnFieldDataMerger dataMerger;

    protected final DataValidator dataValidator;
    
    private final DependencyService dependencyService;

    public GenericService(JpaRepository<DE, Long> repository, DependencyService dependencyService) {
        this.repository = repository;
        this.dependencyService = dependencyService;
        this.dataMerger = new ColumnFieldDataMerger();
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
        List<Dependencies> allDependencies = dependencyService.collectDependencies(id);
        if (isNotEmpty(allDependencies)) {
            throw new CascadeDependenciesException(id, "delete", allDependencies);
        }
        repository.deleteById(id);
    }

}
