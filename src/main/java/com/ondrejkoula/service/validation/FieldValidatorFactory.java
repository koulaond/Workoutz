package com.ondrejkoula.service.validation;

import com.ondrejkoula.service.validation.annotation.Required;
import com.ondrejkoula.service.validation.annotation.RequiredReference;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class FieldValidatorFactory {

    private final Field field;

    private final Object fieldValue;

    public FieldValidatorFactory(Field field, Object fieldValue) {
        this.field = field;
        this.fieldValue = fieldValue;
    }

    public FieldValidator getFieldValidatorForAnnotation(Annotation annotation) {
        if (annotation.annotationType().equals(Required.class)) {
            return new RequiredFieldValidator(field.getName(), fieldValue);
        }
        if (annotation.annotationType().equals(RequiredReference.class)) {
            return new RequiredReferenceFieldValidator(field.getName(), fieldValue);
        }
        return new DefaultFieldValidator();
    }
}
