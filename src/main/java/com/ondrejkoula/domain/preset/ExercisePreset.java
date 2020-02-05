package com.ondrejkoula.domain.preset;

import com.ondrejkoula.domain.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "preset_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class ExercisePreset extends AbstractEntity {

    @Column(name = "preset_type", insertable = false, updatable = false)
    protected String presetType;

    protected String label;
    protected String description;
}
