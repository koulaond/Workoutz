package com.ondrejkoula.service.merger;

import com.ondrejkoula.domain.exercise.ExercisePrescription;
import com.ondrejkoula.domain.exercise.circle.SuperCircle;
import com.ondrejkoula.domain.exercise.weights.Weights;
import com.ondrejkoula.dto.datachange.DataChange;
import com.ondrejkoula.dto.datachange.DataChanges;
import com.ondrejkoula.exception.InconsistentDataFieldTypeOnUpdateException;
import com.ondrejkoula.exception.MissingDataForFieldUpdateException;
import com.ondrejkoula.exception.UnsupportedChangeTypeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ColumnFieldDataMergerTest {

    @Test
    void shouldMergeCorrectly() {
        ColumnFieldDataMerger dataMerger = new ColumnFieldDataMerger();

        SuperCircle target = SuperCircle.builder().status("old").prepareTime(10)
                .workTime(10).restTime(10).setsCount(10).build();

        Map<String, DataChange> dataChangeMap = new HashMap<>();

        dataChangeMap.put("status", DataChange.builder().operation("UPDATE").value("new").build());
        dataChangeMap.put("prepareTime", DataChange.builder().operation("UPDATE").value(20).build());
        dataChangeMap.put("workTime", DataChange.builder().value(20).build()); // missing operation - should be update by default
        dataChangeMap.put("restTime", DataChange.builder().operation("DELETE").build());

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
    void shouldNotUpdateReference() {
        ColumnFieldDataMerger dataMerger = new ColumnFieldDataMerger();

        ExercisePrescription exercisePrescription = ExercisePrescription.builder().id(1L).build();
        Weights target = Weights.builder().status("old").maxTimeSec(10).maxTimeMin(20)
                .exercisePrescription(exercisePrescription).build();

        Map<String, DataChange> dataChangeMap = new HashMap<>();

        dataChangeMap.put("status", DataChange.builder().operation("UPDATE").value("new").build());
        dataChangeMap.put("maxTimeSec", DataChange.builder().operation("UPDATE").value(10).build());
        dataChangeMap.put("maxTimeMin", DataChange.builder().value(20).build()); // missing operation - should be update by default
        dataChangeMap.put("exercisePrescription", DataChange.builder().operation("update").value(2L).build());

        DataChanges dataChanges = DataChanges.builder().changes(dataChangeMap).build();
        dataMerger.mergeSourceToTarget(dataChanges, target);

        Assertions.assertThat(target)
                .hasFieldOrPropertyWithValue("status", "new")
                .hasFieldOrPropertyWithValue("maxTimeSec", 10)
                .hasFieldOrPropertyWithValue("maxTimeMin", 20)
                .hasFieldOrPropertyWithValue("exercisePrescription", exercisePrescription);
    }

    @Test
    void shouldDeleteReference() {
        ColumnFieldDataMerger dataMerger = new ColumnFieldDataMerger();

        ExercisePrescription exercisePrescription = ExercisePrescription.builder().id(1L).build();
        Weights target = Weights.builder().status("old").maxTimeSec(10).maxTimeMin(20)
                .exercisePrescription(exercisePrescription).build();

        Map<String, DataChange> dataChangeMap = new HashMap<>();

        dataChangeMap.put("status", DataChange.builder().operation("UPDATE").value("new").build());
        dataChangeMap.put("maxTimeSec", DataChange.builder().operation("UPDATE").value(10).build());
        dataChangeMap.put("maxTimeMin", DataChange.builder().value(20).build()); // missing operation - should be update by default
        dataChangeMap.put("exercisePrescription", DataChange.builder().operation("delete").build());

        DataChanges dataChanges = DataChanges.builder().changes(dataChangeMap).build();
        dataMerger.mergeSourceToTarget(dataChanges, target);

        Assertions.assertThat(target)
                .hasFieldOrPropertyWithValue("status", "new")
                .hasFieldOrPropertyWithValue("maxTimeSec", 10)
                .hasFieldOrPropertyWithValue("maxTimeMin", 20)
                .hasFieldOrPropertyWithValue("exercisePrescription", null);
    }
    
    @Test
    void whenMissingValueForFieldUpdate_thenMissingDataExceptionIsThrown() {
        ColumnFieldDataMerger dataMerger = new ColumnFieldDataMerger();

        SuperCircle target = SuperCircle.builder().status("old").prepareTime(10)
                .workTime(10).restTime(10).setsCount(10).build();

        Map<String, DataChange> dataChangeMap = new HashMap<>();

        dataChangeMap.put("status", DataChange.builder()
                .operation("UPDATE").build());

        DataChanges dataChanges = DataChanges.builder().changes(dataChangeMap).build();

        assertThrows(MissingDataForFieldUpdateException.class,
                () -> dataMerger.mergeSourceToTarget(dataChanges, target));
    }

    @Test
    void whenIncompatibleValueIsSet_thenInconsistentDataExceptionIsThrown() {
        ColumnFieldDataMerger dataMerger = new ColumnFieldDataMerger();

        SuperCircle target = SuperCircle.builder().status("old").prepareTime(10)
                .workTime(10).restTime(10).setsCount(10).build();

        Map<String, DataChange> dataChangeMap = new HashMap<>();

        dataChangeMap.put("status", DataChange.builder()
                .operation("UPDATE").value(123).build());

        DataChanges dataChanges = DataChanges.builder().changes(dataChangeMap).build();

        assertThrows(InconsistentDataFieldTypeOnUpdateException.class,
                () -> dataMerger.mergeSourceToTarget(dataChanges, target));
    }

    @Test
    void whenUnsupportedOperationIsGiven_thenUnsupportedOperationExceptionIsThrown() {
        ColumnFieldDataMerger dataMerger = new ColumnFieldDataMerger();

        SuperCircle target = SuperCircle.builder().status("old").prepareTime(10)
                .workTime(10).restTime(10).setsCount(10).build();

        Map<String, DataChange> dataChangeMap = new HashMap<>();

        dataChangeMap.put("status", DataChange.builder()
                .operation("UPDATEE").value(123).build());

        DataChanges dataChanges = DataChanges.builder().changes(dataChangeMap).build();

        assertThrows(UnsupportedChangeTypeException.class,
                () -> dataMerger.mergeSourceToTarget(dataChanges, target));
    }
}