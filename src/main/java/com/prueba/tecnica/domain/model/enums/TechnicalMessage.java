package com.prueba.tecnica.domain.model.enums;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum TechnicalMessage {
    PERSON_INFORMATION_NOT_FOUND(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "Person information not found"),
    CLIENT_INFORMATION_NOT_FOUND(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "Client information not found");

    private final HttpStatusCode httpStatusCode;
    private final int codeError;
    private final String message;


    TechnicalMessage(int codeError, HttpStatusCode httpStatusCode, String message) {
        this.codeError = codeError;
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public int getCodeError() {
        return codeError;
    }

    public String getMessage() {
        return message;
    }
}
