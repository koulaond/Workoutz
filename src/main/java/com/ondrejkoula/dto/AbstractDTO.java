package com.ondrejkoula.dto;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.datachange.composition.CompositionChangeValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractDTO implements CompositionChangeValue {

    protected Long id;

    protected String status;

    protected String note;

    public abstract DomainEntity toDomain();
}
