package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PositionOutOfRangeErrorDetailDto extends ErrorDetailDto{

    private final Integer size;

    private final Integer position;

    private final String parentType;

    private final String childType;

    @Builder
    public PositionOutOfRangeErrorDetailDto(String errorMessage, String messageCode, Integer size, 
                                            Integer position, String parentType, String childType) {
        super(errorMessage, messageCode);
        this.size = size;
        this.position = position;
        this.parentType = parentType;
        this.childType = childType;
    }
}
