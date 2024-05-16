package com.prueba.tecnica.application.handler;

import com.prueba.tecnica.application.dto.ClientRequest;
import com.prueba.tecnica.application.dto.ClientResponse;
import com.prueba.tecnica.application.mapper.ClientRequestMapper;
import com.prueba.tecnica.application.mapper.ClientResponseMapper;
import com.prueba.tecnica.application.mapper.PersonRequestMapper;
import com.prueba.tecnica.domain.api.IClientServicePort;
import com.prueba.tecnica.domain.model.Client;
import com.prueba.tecnica.domain.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class ClientHandler implements IClientHandler {

    private final IClientServicePort clientServicePort;
    private final ClientRequestMapper clientRequestMapper;
    private final PersonRequestMapper personRequestMapper;

    @Override
    public void saveClient(ClientRequest clientRequest) {
        Client client = clientRequestMapper.toClient(clientRequest);
        Person person = personRequestMapper.toPerson(clientRequest);
        clientServicePort.saveClient(client, person);
    }

    @Override
    public List<ClientResponse> getAllClients() {
        return clientServicePort.getAllClients();
    }

    @Override
    public void updateClient(Long clientId, ClientRequest clientRequest) {
        Client client = clientRequestMapper.toClient(clientRequest);
        Person person = personRequestMapper.toPerson(clientRequest);

        clientServicePort.updateClient(clientId, client, person);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientServicePort.deleteClient(clientId);
    }
}
