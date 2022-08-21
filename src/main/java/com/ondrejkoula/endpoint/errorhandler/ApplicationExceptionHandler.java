package com.ondrejkoula.endpoint.errorhandler;

import com.ondrejkoula.dto.error.ErrorDetailsDto;
import com.ondrejkoula.exception.DataNotFoundException;
import com.ondrejkoula.exception.ParentNotFoundException;
import com.ondrejkoula.exception.converters.ExceptionToErrorDetailsConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({DataNotFoundException.class, ParentNotFoundException.class})
    public ResponseEntity<ErrorDetailsDto> notFoundHandler(Exception exception) {
        log.warn("Resource not found.", exception);
        return new ResponseEntity<>(convertToApiErrorDto(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({})
    public ResponseEntity<ErrorDetailsDto> badRequestHandler(Exception exception) {
        log.warn("Returning BAD_REQUEST status.", exception);
        return new ResponseEntity<>(convertToApiErrorDto(exception), HttpStatus.BAD_REQUEST);
    }

    private ErrorDetailsDto convertToApiErrorDto(Exception exception) {
        return ExceptionToErrorDetailsConverter.convert(exception);
    }
}
