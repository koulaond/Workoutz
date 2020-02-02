package com.ondrejkoula.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class Muscles extends AbstractEntity {

    @Column(name = "muscles_name")
    private String name;
    private String bodyPart;

    @ManyToMany(mappedBy = "muscles")
    private List<Exercise> exercises;
}
