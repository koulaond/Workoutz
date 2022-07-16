package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.AbstractDTO;
import com.ondrejkoula.dto.datachange.DataChanges;
import com.ondrejkoula.service.GenericService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class CrudEndpoint<DE extends DomainEntity, DTO extends AbstractDTO,
        S extends GenericService<DE>> extends CreateReadDeleteEndpoint<DE, DTO, S> {

    public CrudEndpoint(S service) {
        super(service);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public DTO update(@PathVariable("id") Long id, @RequestBody DataChanges dataChanges) {
        DE updated = service.update(id, dataChanges);
        return toDTO(updated);
    }

}
