package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.AbstractDTO;
import com.ondrejkoula.service.GenericService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CrdEndpoint<DE extends DomainEntity, DTO extends AbstractDTO,
        S extends GenericService<DE>> extends AbstractEndpoint<DE, DTO> {

    protected final S service;

    public CrdEndpoint(S service) {
        this.service = service;
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public DTO get(@PathVariable("id") Long id) {
        DE found = service.findById(id);
        return toDTO(found);
    }

    @GetMapping(value = "/list", produces = "application/json")
    public List<DTO> list() {
        return service.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @PostMapping(produces = "application/json")
    public DTO create(@RequestBody DTO dto) {
        DE toCreate = toDomain(dto);

        DE saved = service.create(toCreate);
        return toDTO(saved);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

}
