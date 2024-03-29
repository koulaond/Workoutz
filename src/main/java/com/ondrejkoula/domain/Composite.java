package com.ondrejkoula.domain;

import java.util.List;

public interface Composite<DE extends DomainEntity> {

     List<? extends CompositionChildExercise<DE>> getChildren();
}
