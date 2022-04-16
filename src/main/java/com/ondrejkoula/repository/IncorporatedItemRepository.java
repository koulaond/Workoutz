package com.ondrejkoula.repository;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.IncorporatedItem;

import java.util.List;

public interface IncorporatedItemRepository<T extends IncorporatedItem<? extends DomainEntity>> {

    List<T> findByParentIdOrderByPosition(Long parentId);

    List<T> findByParentIdAndPositionGreaterThanEqual(Long parentId, Integer position);

    long countByParentId(Long parentId);

    List<T> findByParentIdAndPositionBetween(Long parentId, Integer positionFrom, Integer positionTo);
}
