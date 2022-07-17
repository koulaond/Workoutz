package com.ondrejkoula.service.measurements;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResultValue {

    boolean measurable();

    MeasurementThreshold thresholdType() default MeasurementThreshold.BOTTOM_UP;

    String thresholdFieldName() default "";
}
