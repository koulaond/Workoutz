package com.ondrejkoula.service.validation;

import com.ondrejkoula.domain.DomainEntity;
import com.ondrejkoula.exception.MissingDataOnSaveException;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.apache.commons.collections4.MapUtils.isNotEmpty;

@Slf4j
public class DataValidator {

    public <DE extends DomainEntity> void validateAllMandatoryDataPresent(DE toSave) {
        Map<String, String> validationMessages = new HashMap<>();

        doValidation(toSave, validationMessages);

        if (isNotEmpty(validationMessages)) {
            throw new MissingDataOnSaveException(validationMessages);
        }
    }

    private <DE extends DomainEntity> void doValidation(DE toValidate, Map<String, String> validationMessages) {
        List<Field> allFieldsFromTargetClass = getAllFieldsFromTargetClass(toValidate.getClass());

        allFieldsFromTargetClass.forEach(field
                -> validateSingleFieldValue(field, toValidate, validationMessages));
    }

    private <DE extends DomainEntity> Object getFieldValue(DE toSave, Field field) {
        field.setAccessible(true);
        try {
            return field.get(toSave);
        } catch (IllegalAccessException e) {
            log.warn("Field {} not accessible on class {}.", field.getName(), toSave.getClass().getSimpleName());
            return null;
        }
    }

    private <DE extends DomainEntity> void validateSingleFieldValue(Field field, DE fieldOwner, Map<String, String> validationMessages) {
        Object fieldValue = getFieldValue(fieldOwner, field);
        Annotation[] fieldValidationAnnotations = field.getDeclaredAnnotations();
        FieldValidatorFactory fieldValidatorFactory = new FieldValidatorFactory(field, fieldValue, fieldOwner);

        for (Annotation fieldAnnotation : fieldValidationAnnotations) {
            FieldValidator fieldValidator = fieldValidatorFactory.getFieldValidatorForAnnotation(fieldAnnotation);
            fieldValidator.validateFieldValue(validationMessages);
        }
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
