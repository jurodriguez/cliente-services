package com.prueba.tecnica.infrastructure.exceptionhandler;

import com.prueba.tecnica.domain.exception.TechnicalException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ResponseError> handleUserTechnicalException(
            TechnicalException technicalException) {
        var error = ResponseError.builder().message(technicalException.getTechnicalMessage().getMessage())
                .codeError(technicalException.getTechnicalMessage().getCodeError())
                .dateTime(LocalDateTime.now()).build();
        return new ResponseEntity<>(error, technicalException.getTechnicalMessage().getHttpStatusCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseError> handleConstraintViolationException(ConstraintViolationException ex) {
        String messageError = "Validation error: " + ex.getMessage();
        var error = ResponseError.builder().message(messageError)
                .codeError(HttpStatus.BAD_REQUEST.value())
                .dateTime(LocalDateTime.now()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
