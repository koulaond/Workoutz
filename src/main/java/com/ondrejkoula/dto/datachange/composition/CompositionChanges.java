package com.ondrejkoula.dto.datachange.composition;

import lombok.Data;

import java.util.List;

@Data
public class CompositionChanges {

    private List<CompositionChange> changes;
}
