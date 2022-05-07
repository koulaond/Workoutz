package com.ondrejkoula.dto;

import com.ondrejkoula.domain.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractDTO {

    protected Long id;

    protected String status;

    protected String note;

    public abstract DomainEntity toDomain();
}
