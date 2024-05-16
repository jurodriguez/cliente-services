package com.prueba.tecnica.domain.usecase;

import com.prueba.tecnica.domain.api.IPersonServicePort;
import com.prueba.tecnica.domain.exception.TechnicalException;
import com.prueba.tecnica.domain.model.Client;
import com.prueba.tecnica.domain.model.Person;
import com.prueba.tecnica.domain.spi.persistence.IClientPersistencePort;
import com.prueba.tecnica.factory.FactoryClientDataTest;
import com.prueba.tecnica.factory.FactoryPersonDataTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class ClientUseCaseTest {

    @InjectMocks
    ClientUseCase clientUseCase;

    @Mock
    IClientPersistencePort clientPersistencePort;

    @Mock
    IPersonServicePort personServicePort;

    @Test
    void mustSaveClient() {
        Client client = FactoryClientDataTest.getClient();
        Person person = FactoryPersonDataTest.getPerson();

        clientUseCase.saveClient(client, person);

        //Then
        Mockito.verify(personServicePort).savePerson(Mockito.any(Person.class));
        Mockito.verify(clientPersistencePort).saveClient(Mockito.any(Client.class));
    }

    @Test
    void mustGetAllClients() {
        List<Object[]> list = new ArrayList<>();
        list.add(FactoryClientDataTest.getObject());

        Mockito.when(clientPersistencePort.getAllClientsWithPerson()).thenReturn(list);

        clientUseCase.getAllClients();

        Mockito.verify(clientPersistencePort).getAllClientsWithPerson();
    }

    @Test
    void mustUpdateClient() {
        Client client = FactoryClientDataTest.getClient();
        Client previusClient = FactoryClientDataTest.getClient2();
        Person person = FactoryPersonDataTest.getPerson();

        previusClient.setPersonId(client.getPersonId());
        previusClient.setClientId(client.getClientId());
        previusClient.setPassword(client.getPassword());
        previusClient.setStatus(client.getStatus());

        Mockito.when(clientPersistencePort.getClientById(Mockito.anyLong())).thenReturn(previusClient);
        Mockito.when(clientPersistencePort.getClientById(previusClient.getId())).thenReturn(previusClient);


        clientUseCase.updateClient(previusClient.getId(), client, person);

        //Then
        Mockito.verify(personServicePort).updatePerson(previusClient.getPersonId(), person);
        Mockito.verify(clientPersistencePort).saveClient(previusClient);
        assertEquals(previusClient.getPersonId(), client.getPersonId());
        assertEquals(previusClient.getClientId(), client.getClientId());
        assertEquals(previusClient.getPassword(), client.getPassword());
        assertEquals(previusClient.getStatus(), client.getStatus());
    }

    @Test
    void mustDeleteClient() {
        Client previusClient = FactoryClientDataTest.getClient2();

        Mockito.when(clientPersistencePort.getClientById(previusClient.getId())).thenReturn(previusClient);

        clientUseCase.deleteClient(previusClient.getId());

        //Then
        Mockito.verify(personServicePort).deletePerson(Mockito.anyLong());
        Mockito.verify(clientPersistencePort).deleteClient(previusClient);
    }

    @Test
    void mustDeleteClientTechnicalException() {
        Client previusClient = FactoryClientDataTest.getClient2();

        assertThrows(TechnicalException.class, () -> clientUseCase.deleteClient(previusClient.getId()));

        //Then
        Mockito.verify(personServicePort, Mockito.never()).deletePerson(Mockito.anyLong());
        Mockito.verify(clientPersistencePort, Mockito.never()).deleteClient(previusClient);
    }
}
