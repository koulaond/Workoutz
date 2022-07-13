package com.ondrejkoula.service.validation;

import com.ondrejkoula.service.validation.annotation.Before;
import com.ondrejkoula.service.validation.annotation.Required;
import com.ondrejkoula.service.validation.annotation.RequiredReference;
import com.ondrejkoula.service.validation.annotation.RequiredReferences;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class FieldValidatorFactory {

    private final Field field;

    private final Object fieldValue;

    private final Object fieldOwner;

    public FieldValidatorFactory(Field field, Object fieldValue, Object fieldOwner) {
        this.field = field;
        this.fieldValue = fieldValue;
        this.fieldOwner = fieldOwner;
    }

    public FieldValidator getFieldValidatorForAnnotation(Annotation annotation) {
        if (annotation.annotationType().equals(Required.class)) {
            return new RequiredFieldValidator(field.getName(), fieldValue);
        }
        if (annotation.annotationType().equals(RequiredReference.class)) {
            return new RequiredReferenceFieldValidator(field.getName(), fieldValue);
        }
        if (annotation.annotationType().equals(RequiredReferences.class)) {
            return new RequiredReferenceFieldValidator(field.getName(), fieldValue);
        }
        if (annotation.annotationType().equals(Before.class)) {
            return new DateFieldIsBeforeOtherValidator(field, fieldOwner);
        }
        return new DefaultFieldValidator();
    }
}
