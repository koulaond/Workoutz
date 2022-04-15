package com.ondrejkoula.repository;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.IncorporatedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncorporatedItemRepository<T extends IncorporatedItem<? extends DomainEntity>, ID extends Long> extends JpaRepository<T, ID> {

    List<T> findByParentOrderByPosition(Long parentId);

    List<T> findByParentAndPositionGreaterThan(Long parentId, Integer position);

    long countByParent(Long parentId);

    List<T> findByParentAndPositionBetween(Long parentId, Integer positionFrom, Integer positionTo);
}
