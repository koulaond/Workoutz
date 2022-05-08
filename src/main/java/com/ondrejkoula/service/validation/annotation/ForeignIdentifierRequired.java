package com.ondrejkoula.service.validation.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface ForeignIdentifierRequired {
}
