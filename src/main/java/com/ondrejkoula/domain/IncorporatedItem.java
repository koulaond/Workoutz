package com.ondrejkoula.domain;

public interface IncorporatedItem<PARENT> {

    PARENT getParent();

    Integer getPosition();
}
