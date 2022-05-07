package com.ondrejkoula.endpoint.mapper;


import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.AbstractDTO;

public interface DomainMapper<DE extends DomainEntity, DTO extends AbstractDTO> {

    DTO mapToDto(DE domainEntity);

    DE mapToDomain(DTO dto);
}
