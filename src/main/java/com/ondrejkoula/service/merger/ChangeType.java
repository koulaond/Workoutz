package com.ondrejkoula.service.merger;

import com.ondrejkoula.exception.UnsupportedChangeTypeException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum ChangeType {
    UPDATE("update"), DELETE("delete");
    
    private final String type;

    ChangeType(String type) {
        this.type = type;
    }
    
    static ChangeType fromString(String type) {
        if (StringUtils.isBlank(type)) {
            return UPDATE; // Update by default
        }
        return Arrays.stream(values())
                .filter(changeType -> changeType.type.equals(StringUtils.toRootLowerCase(type)))
                .findFirst()
                .orElseThrow(() -> new UnsupportedChangeTypeException(type));
    }
}
