package com.ondrejkoula.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class DomainEntity implements Loggable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "status")
    protected String status;

    @Column(name = "note")
    protected String note;

}
