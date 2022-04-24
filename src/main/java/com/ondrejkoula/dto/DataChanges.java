package com.ondrejkoula.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class DataChanges {

    private Map<String, DataChange> changes;
}
