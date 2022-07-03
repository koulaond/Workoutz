package com.ondrejkoula.dto.datachange.composition;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CompositionChanges {

    List<CompositionChangeRaw> changes;

    @Builder
    public CompositionChanges(List<CompositionChangeRaw> changes) {
        this.changes = changes;
    }
}
