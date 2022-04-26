package com.ondrejkoula.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class DataChanges {

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
