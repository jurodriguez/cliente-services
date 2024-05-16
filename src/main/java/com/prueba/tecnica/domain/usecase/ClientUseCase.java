package com.prueba.tecnica.domain.usecase;

import com.prueba.tecnica.application.dto.ClientResponse;
import com.prueba.tecnica.domain.api.IClientServicePort;
import com.prueba.tecnica.domain.api.IPersonServicePort;
import com.prueba.tecnica.domain.exception.TechnicalException;
import com.prueba.tecnica.domain.model.Client;
import com.prueba.tecnica.domain.model.Person;
import com.prueba.tecnica.domain.model.enums.TechnicalMessage;
import com.prueba.tecnica.domain.spi.persistence.IClientPersistencePort;

import java.util.ArrayList;
import java.util.List;

public class ClientUseCase implements IClientServicePort {

    private final IClientPersistencePort clientPersistencePort;

    private final IPersonServicePort personServicePort;

    public ClientUseCase(IClientPersistencePort clientPersistencePort, IPersonServicePort personServicePort) {
        this.clientPersistencePort = clientPersistencePort;
        this.personServicePort = personServicePort;
    }

    @Override
    public void saveClient(Client client, Person person) {
        personServicePort.savePerson(person);
        client.setPersonId(person.getPersonId());
        clientPersistencePort.saveClient(client);
    }

    @Override
    public List<ClientResponse> getAllClients() {
        List<Object[]> objectList = clientPersistencePort.getAllClientsWithPerson();
        return converter(objectList);
    }

    @Override
    public void updateClient(Long clientId, Client client, Person person) {
        Client previusClient = getPreviusClient(clientId);
        updateData(previusClient, client);
        clientPersistencePort.saveClient(previusClient);
        personServicePort.updatePerson(previusClient.getPersonId(), person);
    }

    @Override
    public void deleteClient(Long clientId) {
        Client previusClient = getPreviusClient(clientId);
        personServicePort.deletePerson(previusClient.getPersonId());
        clientPersistencePort.deleteClient(previusClient);
    }

    public List<ClientResponse> converter(List<Object[]> listObject) {
        List<ClientResponse> list = new ArrayList<>();
        for (Object[] obj : listObject) {
            list.add(ClientResponse.fromClient(obj));
        }
        return list;
    }

    private Client getPreviusClient(Long id) {
        Client previusClient = clientPersistencePort.getClientById(id);
        if (previusClient == null) throw new TechnicalException(TechnicalMessage.CLIENT_INFORMATION_NOT_FOUND);
        return previusClient;
    }

    private void updateData(Client previusClient, Client client) {
        previusClient.setPersonId(client.getPersonId());
        previusClient.setClientId(client.getClientId());
        previusClient.setPassword(client.getPassword());
        previusClient.setStatus(client.getStatus());
    }
}
