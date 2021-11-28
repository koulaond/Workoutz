package com.ondrejkoula.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@MappedSuperclass
public abstract class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected Status status;
}
