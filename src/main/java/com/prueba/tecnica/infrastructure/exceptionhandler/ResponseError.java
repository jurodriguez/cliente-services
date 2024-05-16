package com.prueba.tecnica.infrastructure.exceptionhandler;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ResponseError {

    private String message;

    private int codeError;

    private LocalDateTime dateTime;
}
