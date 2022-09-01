package com.ondrejkoula.service.dependencies;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.EntityType;
import com.ondrejkoula.dto.Dependencies;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class DependenciesCollector {

    public List<Dependencies> collectDependencies(Long parentEntityId) {
        List<Dependencies> dependenciesList = new ArrayList<>();
        
        doCollect(parentEntityId, dependenciesList);
        return dependenciesList;
    }


    protected void registerDependenciesForEntityType(EntityType entityType, List<? extends DomainEntity> entities, List<Dependencies> dependenciesList) {
        Dependencies dependencies = Dependencies.builder()
                .type(entityType)
                .ids(entities.stream().map(DomainEntity::getId).collect(toList()))
                .build();
        dependenciesList.add(dependencies);
    }
    
    public abstract void doCollect(Long parentEntityId, List<Dependencies> dependenciesList);
    
}
