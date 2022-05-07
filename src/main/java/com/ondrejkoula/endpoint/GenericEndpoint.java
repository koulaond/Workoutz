package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.AbstractDTO;
import com.ondrejkoula.dto.DataChanges;
import com.ondrejkoula.service.GenericService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericEndpoint<DE extends DomainEntity, DTO extends AbstractDTO,
        S extends GenericService<DE, ? extends JpaRepository<DE, Long>>> {

    protected final S service;

    public GenericEndpoint(S service) {
        this.service = service;
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    DTO get(@PathVariable("id") Long id) {
        DE found = service.findById(id);
        return toDTO(found);
    }

    @GetMapping(value = "/list", produces = "application/json")
    List<DTO> list() {
        return service.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @PostMapping(produces = "application/json")
    public DTO create(@RequestBody DTO dto) {
        DE toCreate = toDomain(dto);

        DE saved = service.create(toCreate);
        return toDTO(saved);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public DTO update(@PathVariable("id") Long id, @RequestBody DataChanges dataChanges) {
        DE updated = service.update(id, dataChanges);
        return toDTO(updated);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    protected abstract DE toDomain(DTO dto);

    protected abstract DTO toDTO(DE domainEntity);

}
