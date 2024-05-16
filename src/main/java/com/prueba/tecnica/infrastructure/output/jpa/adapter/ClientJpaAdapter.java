package com.prueba.tecnica.infrastructure.output.jpa.adapter;

import com.prueba.tecnica.domain.model.Client;
import com.prueba.tecnica.domain.spi.persistence.IClientPersistencePort;
import com.prueba.tecnica.infrastructure.output.jpa.entity.ClientEntity;
import com.prueba.tecnica.infrastructure.output.jpa.mapper.ClientEntityMapper;
import com.prueba.tecnica.infrastructure.output.jpa.repository.IClientRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClientJpaAdapter implements IClientPersistencePort {

    private final IClientRepository clientRepository;

    private final ClientEntityMapper clientEntityMapper;

    @Override
    public void saveClient(Client client) {
        clientRepository.save(clientEntityMapper.toEntity(client));
    }

    @Override
    public Client getClientById(Long id) {
        Optional<ClientEntity> optionalClient = clientRepository.findById(id);
        ClientEntity clientEntity = optionalClient.orElse(null);
        return clientEntityMapper.toClient(clientEntity);
    }

    @Override
    public List<Object[]> getAllClientsWithPerson() {
        return clientRepository.getAllClientsWithPerson();
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(clientEntityMapper.toEntity(client));
    }

}
