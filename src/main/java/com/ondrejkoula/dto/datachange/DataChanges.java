package com.ondrejkoula.dto.datachange;

import com.ondrejkoula.dto.datachange.composition.CompositionChangeValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataChanges implements CompositionChangeValue {

    private Map<String, DataChange> changes;
    
}
