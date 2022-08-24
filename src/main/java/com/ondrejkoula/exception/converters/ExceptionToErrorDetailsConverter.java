package com.ondrejkoula.exception.converters;

import com.ondrejkoula.dto.error.ErrorDetailDto;
import com.ondrejkoula.dto.error.ErrorDetailDtoFactory;
import com.ondrejkoula.exception.*;
import com.ondrejkoula.exception.validation.ValidationException;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ExceptionToErrorDetailsConverter {

    private static final DefaultConversionService CONVERSION_SERVICE = new DefaultConversionService();

    private static final Map<Class<Exception>, Function<Exception, ErrorDetailDto>> converters = new HashMap<>();

    static {
        addConverter(Exception.class, ErrorDetailDtoFactory::fromGeneralError);
        addConverter(DataIntegrityViolationException.class, ErrorDetailDtoFactory::fromDataIntegrityViolationError);
        addConverter(ValidationException.class, ErrorDetailDtoFactory::fromValidationError);
        addConverter(CascadeDependenciesException.class, ErrorDetailDtoFactory::fromCascadeDependenciesError);
        addConverter(DataNotFoundException.class, ErrorDetailDtoFactory::fromDataNotFoundError);
        addConverter(InconsistentDataUpdateException.class, ErrorDetailDtoFactory::fromInconsistentDataUpdateError);
        addConverter(InconsistentPositionsException.class, ErrorDetailDtoFactory::fromInconsistentPositionsError);
        addConverter(IncorrectParentException.class, ErrorDetailDtoFactory::fromIncorrectParentError);
        addConverter(MissingDataForFieldUpdateException.class, ErrorDetailDtoFactory::fromMissingDataForFieldUpdateError);
        addConverter(MissingDataOnSaveException.class, ErrorDetailDtoFactory::fromMissingDataOnSaveError);
        addConverter(OutOfTimeWindowException.class, ErrorDetailDtoFactory::fromOutOfTimeWindowError);
        addConverter(PositionOutOfRangeException.class, ErrorDetailDtoFactory::fromPositionOutOfRangeError);
        addConverter(UnsupportedChangeTypeException.class, ErrorDetailDtoFactory::fromUnsupportedChangeTypeError);
        addConverter(UnsupportedCompositeChangeTypeException.class, ErrorDetailDtoFactory::fromUnsupportedCompositeChangeTypeError);
    }

    private static <T extends Exception, E extends ErrorDetailDto> void addConverter(Class<T> exceptionClazz, Function<T, E> exceptionConverter) {
        CONVERSION_SERVICE.addConverter(exceptionClazz, ErrorDetailDto.class, exceptionConverter::apply);
    }

    public static ErrorDetailDto convert(Exception ex) {
        return CONVERSION_SERVICE.convert(ex, ErrorDetailDto.class);
    }
}
