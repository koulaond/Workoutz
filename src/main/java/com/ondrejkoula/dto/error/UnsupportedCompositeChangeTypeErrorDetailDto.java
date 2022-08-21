package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UnsupportedCompositeChangeTypeErrorDetailDto extends ErrorDetailDto {

    private final String unsupportedChangeType;

    private final Long parentEntityId;

    @Builder
    public UnsupportedCompositeChangeTypeErrorDetailDto(String errorMessage, String messageCode,
                                                        String unsupportedChangeType, Long parentEntityId) {
        super(errorMessage, messageCode);
        this.unsupportedChangeType = unsupportedChangeType;
        this.parentEntityId = parentEntityId;
    }
}
