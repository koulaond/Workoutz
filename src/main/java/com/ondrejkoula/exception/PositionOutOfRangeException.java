package com.ondrejkoula.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionOutOfRangeException extends RuntimeException {

    private static final String UNKNOWN_PARENT = "unknownParent";

    private static final String UNKNOWN_CHILD = "unknownChild";

    private final Integer size;

    private final Integer position;

    private final String parentType;

    private final String childType;

    public PositionOutOfRangeException(Integer size, Integer position, String parentType, String childType) {
        this.size = size;
        this.position = position;
        this.parentType = parentType;
        this.childType = childType;
    }

    public PositionOutOfRangeException(Integer size, Integer position) {
        this(size, position, UNKNOWN_PARENT, UNKNOWN_CHILD);
    }
}
