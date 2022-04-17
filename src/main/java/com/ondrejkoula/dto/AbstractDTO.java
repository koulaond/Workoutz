package com.ondrejkoula.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AbstractDTO {

    protected Long id;

    protected String status;

    protected String note;
}
