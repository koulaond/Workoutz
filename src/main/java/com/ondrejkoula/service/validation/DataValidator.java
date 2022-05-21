package com.ondrejkoula.service.validation;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.exception.MissingDataOnSaveException;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.apache.commons.collections4.MapUtils.isNotEmpty;

public class DataValidator {

    public <DE extends DomainEntity> void validateAllMandatoryDataPresent(DE toSave) {
        Map<String, String> validationMessages = new HashMap<>();

        doValidation(toSave, validationMessages);

        if (isNotEmpty(validationMessages)) {
            throw new MissingDataOnSaveException(validationMessages);
        }
    }

    private <DE extends DomainEntity> void doValidation(DE toSave, Map<String, String> validationMessages) {
        List<Field> allFieldsFromTargetClass = getAllFieldsFromTargetClass(toSave.getClass());

        allFieldsFromTargetClass.forEach(field -> {
            Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
        });
    }

    private List<Field> getAllFieldsFromTargetClass(Class<?> targetClass) {
        List<Field> classFields = new ArrayList<>();

        while (targetClass != null) {
            classFields.addAll(asList(targetClass.getDeclaredFields()));
            targetClass = targetClass.getSuperclass();
        }

        return classFields;
    }
}
