package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.dto.datachange.DataChanges;

public interface DataMerger {
    <DE extends DomainEntity> void mergeSourceToTarget(DataChanges dataChanges, DE target);

}
