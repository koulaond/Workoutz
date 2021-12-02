package com.ondrejkoula.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@MappedSuperclass
public abstract class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "status")
    protected String status;
}
