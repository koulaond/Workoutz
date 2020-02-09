package com.ondrejkoula.dto.preset;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ondrejkoula.dto.create.StandardSetPresetCreateDto;
import com.ondrejkoula.dto.create.SuperSetPresetCreateDto;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "presetType")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = StandardSetPresetCreateDto.class, name = "StandardSet"),
                @JsonSubTypes.Type(value = SuperSetPresetCreateDto.class, name = "SuperSet")
        }
)
public abstract class ExercisePresetDto {
    protected Long id;
    protected String label;
    protected String description;
}
