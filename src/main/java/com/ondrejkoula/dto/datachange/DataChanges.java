package com.ondrejkoula.dto.datachange;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.ondrejkoula.dto.datachange.composition.CompositionChangeValue;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class DataChanges implements CompositionChangeValue {

    private Map<String, DataChange> changes;

    @Builder
    public DataChanges(Map<String, DataChange> changes) {
        this.changes = changes;
    }

    @JsonAnyGetter
    public Map<String, DataChange> getChanges() {
        return changes;
    }

    @JsonAnySetter
    public void setChanges(Map<String, DataChange> changes) {
        this.changes = changes;
    }
}
