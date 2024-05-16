package com.prueba.tecnica.domain.api;

import com.prueba.tecnica.application.dto.ClientResponse;
import com.prueba.tecnica.domain.model.Client;
import com.prueba.tecnica.domain.model.Person;

import java.util.List;

public interface IClientServicePort {

    void saveClient(Client client, Person person);

    List<ClientResponse> getAllClients();

    void updateClient(Long clientId, Client client, Person person);

    void deleteClient(Long clientId);
}
