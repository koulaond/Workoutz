package com.ondrejkoula.dto.datachange.composition;

import lombok.Data;

@Data
public class UpdatePositionCompositionChange {

    private Long childId;

    private Integer newPosition;
}
