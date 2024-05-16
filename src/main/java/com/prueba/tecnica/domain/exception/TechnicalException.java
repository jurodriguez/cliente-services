package com.prueba.tecnica.domain.exception;

import com.prueba.tecnica.domain.model.enums.TechnicalMessage;

public class TechnicalException extends RuntimeException {
    private final TechnicalMessage technicalMessage;

    public TechnicalException(TechnicalMessage technicalMessage){
        super(technicalMessage.getMessage());
        this.technicalMessage = technicalMessage;
    }

    public TechnicalMessage getTechnicalMessage() {
        return technicalMessage;
    }
}
