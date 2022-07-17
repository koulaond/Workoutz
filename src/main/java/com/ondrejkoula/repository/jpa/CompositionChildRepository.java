package com.ondrejkoula.repository.jpa;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.domain.CompositionChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CompositionChildRepository<T extends CompositionChild<? extends DomainEntity>> extends JpaRepository<T, Long> {

    List<T> findByParentIdOrderByPosition(Long parentId);

    List<T> findByParentIdAndPositionGreaterThanEqual(Long parentId, Integer position);

    int countByParentId(Long parentId);

    List<T> findByParentIdAndPositionBetween(Long parentId, Integer positionFrom, Integer positionTo);
}
