package com.ondrejkoula.dto.datachange;

import lombok.Data;

import java.util.List;

@Data
public class CompositeDataChanges {

    private List<CompositeDataChange> changes;
}
