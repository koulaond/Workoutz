package com.ondrejkoula.endpoint.processor;

import com.ondrejkoula.domain.CompositionChild;
import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.datachange.composition.DeleteChildCompositionChange;
import com.ondrejkoula.dto.datachange.composition.UpdateChildCompositionChange;
import com.ondrejkoula.dto.datachange.composition.UpdatePositionCompositionChange;

public interface CompositionDataChangeProcessor<P extends DomainEntity, CH extends CompositionChild<P>> {

    void processPositionChange(UpdatePositionCompositionChange changePositionDataChange);

    void processAddNewChild(Integer index, CH childToAdd);

    void processUpdateChild(UpdateChildCompositionChange updateChildCompositionChange);

    void processDeleteChild(DeleteChildCompositionChange deleteChildCompositionChange);

}
