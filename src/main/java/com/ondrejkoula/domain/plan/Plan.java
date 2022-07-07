package com.ondrejkoula.domain.plan;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.AbstractDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "plans")
public class Plan extends DomainEntity {



    @Override
    public AbstractDTO toDTO() {
        return null;
    }

    @Override
    public String loggableString() {
        return null;
    }
}
