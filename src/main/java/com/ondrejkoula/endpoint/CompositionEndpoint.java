package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.AbstractDTO;
import com.ondrejkoula.dto.datachange.composition.CompositionChanges;
import com.ondrejkoula.service.GenericService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class CompositionEndpoint<DE extends DomainEntity, DTO extends AbstractDTO,
        PS extends GenericService<DE>> extends CrdEndpoint<DE, DTO, PS> {

    public CompositionEndpoint(PS service) {
        super(service);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public DTO update(@PathVariable("id") Long id, @RequestBody CompositionChanges dataChanges) {
        return null;
    }
}
