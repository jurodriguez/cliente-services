package com.prueba.tecnica.application.handler;

import com.prueba.tecnica.application.dto.ClientRequest;
import com.prueba.tecnica.application.dto.ClientResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface IClientHandler {
    void saveClient(@Valid ClientRequest clientRequest);

    List<ClientResponse> getAllClients();

    void updateClient(Long clientId, @Valid ClientRequest clientRequest);

    void deleteClient(Long clientId);
}
