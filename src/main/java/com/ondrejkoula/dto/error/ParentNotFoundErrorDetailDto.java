package com.ondrejkoula.dto.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ParentNotFoundErrorDetailDto extends ErrorDetailDto {

    private final Long id;

    private final String type;

    @Builder
    public ParentNotFoundErrorDetailDto(String errorMessage, String messageCode, Long id, String type) {
        super(errorMessage, messageCode);
        this.id = id;
        this.type = type;
    }
}
