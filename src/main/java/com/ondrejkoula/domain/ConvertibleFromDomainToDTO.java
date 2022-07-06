package com.ondrejkoula.domain;

import com.ondrejkoula.dto.ConvertibleFromDTOToDomain;

public interface ConvertibleFromDomainToDTO {

    ConvertibleFromDTOToDomain toDTO();
}
