package com.ondrejkoula.service.merger;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.lang.reflect.Field;

public class FieldValueChangerFactory {

    private static final FieldValueChangerFactory factory = new FieldValueChangerFactory();
    
     FieldValueChanger getFieldValueChanger(Field field) {
        if (field.isAnnotationPresent(Column.class)) {
            return new PrimitiveFieldValueChanger();

        } else if (field.isAnnotationPresent(ManyToOne.class)) {
            return new ReferenceFieldValueChanger();
     
        } else {
            return new NoFieldValueChanger();
        }
    }
    
    static FieldValueChangerFactory getFactory() {
         return factory;
    }

}
