package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class IncorrectParentErrorDetailDto extends ErrorDetailDto{

    private final Long childId;

    private final Long invalidParentId;

    @Builder
    public IncorrectParentErrorDetailDto(String errorMessage, String messageCode, Long childId, Long invalidParentId) {
        super(errorMessage, messageCode);
        this.childId = childId;
        this.invalidParentId = invalidParentId;
    }
}
