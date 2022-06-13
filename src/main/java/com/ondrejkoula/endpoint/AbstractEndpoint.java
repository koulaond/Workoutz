package com.ondrejkoula.endpoint;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.AbstractDTO;

public abstract class AbstractEndpoint<DE extends DomainEntity, DTO extends AbstractDTO> {

    protected abstract DE toDomain(DTO dto);

    protected abstract DTO toDTO(DE domainEntity);

}
