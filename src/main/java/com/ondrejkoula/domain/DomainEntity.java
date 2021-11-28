package com.ondrejkoula.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public abstract class DomainEntity {

    @Id
    protected UUID id;
}
