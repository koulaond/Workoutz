package com.ondrejkoula.dto.datachange.composition;

import lombok.Data;

@Data
public class DeleteChildCompositionChange implements CompositionChangeValue {

    private Long id;
}
