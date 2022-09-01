package com.ondrejkoula.dto;

import com.ondrejkoula.domain.EntityType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Dependencies {
    
    private EntityType type;
    
    private List<Long> ids;
}
