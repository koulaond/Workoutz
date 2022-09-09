package com.ondrejkoula.endpoint.errorhandler;

import com.ondrejkoula.dto.error.ErrorDetailDto;
import com.ondrejkoula.exception.*;
import com.ondrejkoula.exception.converters.ExceptionToErrorDetailsConverter;
import com.ondrejkoula.exception.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({DataNotFoundException.class, ParentNotFoundException.class})
    public ResponseEntity<ErrorDetailDto> notFoundHandler(Exception exception) {
        log.warn("Resource not found.", exception);
        return new ResponseEntity<>(convertToApiErrorDto(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ValidationException.class, CascadeDependenciesException.class, InconsistentDataFieldTypeOnUpdateException.class,
            InconsistentPositionsException.class, IncorrectParentException.class, InternalException.class, 
            MissingDataForFieldUpdateException.class, MissingDataOnSaveException.class, OutOfTimeWindowException.class, 
            PositionOutOfRangeException.class, UnsupportedChangeTypeException.class, UnsupportedCompositeChangeTypeException.class})
    public ResponseEntity<ErrorDetailDto> badRequestHandler(Exception exception) {
        log.warn("Returning BAD_REQUEST status.", exception);
        return new ResponseEntity<>(convertToApiErrorDto(exception), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorDetailDto> dataIntegrityViolationErrorHandler(Exception exception) {
        log.warn("Returning BAD_REQUEST status.", exception);
        return new ResponseEntity<>(convertToApiErrorDto(exception), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDetailDto> internalErrorHandler(Exception exception) {
        log.warn("Returning BAD_REQUEST status.", exception);
        return new ResponseEntity<>(convertToApiErrorDto(exception), HttpStatus.BAD_REQUEST);
    }

    private ErrorDetailDto convertToApiErrorDto(Exception exception) {
        return ExceptionToErrorDetailsConverter.convert(exception);
    }
}
