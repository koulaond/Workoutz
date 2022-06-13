package com.ondrejkoula.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class CompositionChild<PARENT extends DomainEntity> extends DomainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    protected PARENT parent;

    @Column(name = "position")
    protected Integer position;

    public CompositionChild(Long id, String status, String note, PARENT parent, Integer position) {
        super(id, status, note);
        this.parent = parent;
        this.position = position;
    }
}
