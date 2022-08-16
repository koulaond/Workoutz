package com.ondrejkoula.exception.converters;

import com.ondrejkoula.dto.error.ErrorDetailDto;
import com.ondrejkoula.dto.error.ErrorDetailDtoFactory;
import com.ondrejkoula.exception.validation.ValidationException;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.function.Function;

public class ExceptionToErrorDetailsConverter {

    private static final DefaultConversionService CONVERSION_SERVICE = new DefaultConversionService();

    static{
        addConverter(Exception.class, ErrorDetailDtoFactory::fromGeneralError);
        addConverter(ValidationException.class, ErrorDetailDtoFactory::fromValidationError);
    }

    private static final <T extends Exception> void addConverter(Class<T> exceptionClazz, Function<T, ErrorDetailDto> exceptionConverter) {
        CONVERSION_SERVICE.addConverter(exceptionClazz, ErrorDetailDto.class, exceptionConverter::apply);
    }
}
