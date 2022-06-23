package com.ondrejkoula.dto.datachange.composition;

import com.ondrejkoula.dto.AbstractDTO;
import lombok.Value;

@Value
public class AddNewChildCompositionChange<T extends AbstractDTO> {

    Long position;

    T data;
}
