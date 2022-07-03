package com.ondrejkoula.dto.datachange.composition;

import com.ondrejkoula.dto.AbstractDTO;
import lombok.Data;

@Data
public class AddNewChildCompositionChange<T extends AbstractDTO> {

    Long position;

    T data;
}
