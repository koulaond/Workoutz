package com.ondrejkoula.dto;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.datachange.composition.CompositionChangeValue;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractDTO implements CompositionChangeValue, ConvertibleFromDTOToDomain {

    protected Long id;

    protected String status;

    public AbstractDTO(Long id, String status, String note) {
        this.id = id;
        this.status = status;
        this.note = note;
    }



    protected String note;

    @Override
    public abstract DomainEntity toDomain();
}
