package com.ondrejkoula.dto.datachange.composition;

import com.ondrejkoula.dto.datachange.DataChanges;
import lombok.Data;

@Data
public class UpdateChildCompositionChange {

    private Long childId;

    private DataChanges childChanges;
}
