package com.ondrejkoula.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@MappedSuperclass
public abstract class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "status")
    protected Status status;
}
