package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.exercise.circle.SuperCircle;
import com.ondrejkoula.dto.DataChange;
import com.ondrejkoula.dto.DataChanges;
import com.ondrejkoula.exception.InconsistentDataUpdateException;
import com.ondrejkoula.exception.MissingDataForFieldException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.ondrejkoula.dto.DataChange.ChangeType.DELETE;
import static com.ondrejkoula.dto.DataChange.ChangeType.UPDATE;

class DataMergerTest {

    @Test
    void shouldMergeCorrectly() {
        DataMerger dataMerger = new DataMerger();

        SuperCircle target = SuperCircle.builder().status("old").prepareTime(10)
                .workTime(10).restTime(10).setsCount(10).build();

        Map<String, DataChange> dataChangeMap = new HashMap<>();

        dataChangeMap.put("status", DataChange.builder()
                .changeType(UPDATE).value("new").build());

        dataChangeMap.put("prepareTime", DataChange.builder()
                .changeType(UPDATE).value(20).build());

        dataChangeMap.put("workTime", DataChange.builder()
                .changeType(UPDATE).value(20).build());

        dataChangeMap.put("restTime", DataChange.builder()
                .changeType(DELETE).build());

        DataChanges dataChanges = DataChanges.builder().changes(dataChangeMap).build();

        dataMerger.mergeSourceToTarget(dataChanges, target);

        Assertions.assertThat(target)
                .hasFieldOrPropertyWithValue("status", "new")
                .hasFieldOrPropertyWithValue("prepareTime", 20)
                .hasFieldOrPropertyWithValue("workTime", 20)
                .hasFieldOrPropertyWithValue("restTime", null)
                .hasFieldOrPropertyWithValue("setsCount", 10);
    }

    @Test
    void whenMissingValueForFieldUpdate_thenMissingDataExceptionIsThrown() {
        DataMerger dataMerger = new DataMerger();

        SuperCircle target = SuperCircle.builder().status("old").prepareTime(10)
                .workTime(10).restTime(10).setsCount(10).build();

        Map<String, DataChange> dataChangeMap = new HashMap<>();

        dataChangeMap.put("status", DataChange.builder()
                .changeType(UPDATE).build());

        DataChanges dataChanges = DataChanges.builder().changes(dataChangeMap).build();

        org.junit.jupiter.api.Assertions.assertThrows(MissingDataForFieldException.class,
                () -> dataMerger.mergeSourceToTarget(dataChanges, target));
    }

    @Test
    void whenIncompatibleValueIsSet_thenInconsistentDataExceptionIsThrown() {
        DataMerger dataMerger = new DataMerger();

        SuperCircle target = SuperCircle.builder().status("old").prepareTime(10)
                .workTime(10).restTime(10).setsCount(10).build();

        Map<String, DataChange> dataChangeMap = new HashMap<>();

        dataChangeMap.put("status", DataChange.builder()
                .changeType(UPDATE).value(123).build());

        DataChanges dataChanges = DataChanges.builder().changes(dataChangeMap).build();

        org.junit.jupiter.api.Assertions.assertThrows(InconsistentDataUpdateException.class,
                () -> dataMerger.mergeSourceToTarget(dataChanges, target));
    }
}