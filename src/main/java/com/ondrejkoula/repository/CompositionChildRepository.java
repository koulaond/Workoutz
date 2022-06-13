package com.ondrejkoula.repository;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.CompositionChild;

import java.util.List;

public interface CompositionChildRepository<T extends CompositionChild<? extends DomainEntity>> {

    List<T> findByParentIdOrderByPosition(Long parentId);

    List<T> findByParentIdAndPositionGreaterThanEqual(Long parentId, Integer position);

    long countByParentId(Long parentId);

    List<T> findByParentIdAndPositionBetween(Long parentId, Integer positionFrom, Integer positionTo);
}
