package com.ondrejkoula.service;

import com.ondrejkoula.domain.IncorporatedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class IncorporatedItemService<CHILD extends IncorporatedItem<?>, REPOSITORY extends JpaRepository<CHILD, Long>>
        extends GenericService<CHILD, REPOSITORY> {

    public IncorporatedItemService(REPOSITORY repository) {
        super(repository);
    }

    public abstract List<CHILD> findByParentId(Long parentId);

    public abstract CHILD assignNewItemToParent(Long parentId, CHILD newItem);

    public abstract CHILD changeItemPosition(Long itemId, Integer newPosition);

    public abstract void removeExistingItemFromParent(Long itemId);
}
