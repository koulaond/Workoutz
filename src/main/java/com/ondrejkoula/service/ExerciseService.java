package com.ondrejkoula.service;

import com.ondrejkoula.domain.IncorporatedItem;

import java.util.List;

public abstract class ExerciseService< CHILD extends IncorporatedItem<?>> {

    public abstract List<CHILD> findByParentId(Long parentId);

    public abstract CHILD assignNewItemToParent(Long parentId, CHILD newItem);

    public abstract CHILD changeItemPosition(Long itemId, Integer newPosition);

    public abstract void removeExistingItemFromParent(Long itemId);
}
