package com.prueba.tecnica.domain.spi.persistence;

import com.prueba.tecnica.domain.model.Client;

import java.util.List;

public interface IClientPersistencePort {

    void saveClient(Client client);

    Client getClientById(Long id);

    List<Object[]> getAllClientsWithPerson();

    void deleteClient(Client client);
}
