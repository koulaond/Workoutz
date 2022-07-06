package com.ondrejkoula.domain;

import com.ondrejkoula.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DomainEntity implements Loggable, ConvertibleFromDomainToDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "status")
    protected String status;

    @Column(name = "note")
    protected String note;


    @Override
  public abstract AbstractDTO toDTO();
}
